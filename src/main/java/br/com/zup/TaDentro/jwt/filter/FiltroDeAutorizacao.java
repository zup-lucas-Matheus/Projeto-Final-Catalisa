package br.com.zup.TaDentro.jwt.filter;


import br.com.zup.TaDentro.jwt.exeptionAuthentication.MensagemTokenInvalido;
import io.jsonwebtoken.Claims;
import lombok.SneakyThrows;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FiltroDeAutorizacao extends BasicAuthenticationFilter {

    private JwtComponent jwtComponente;
    private UserDetailsService userDetailsService;

    public FiltroDeAutorizacao(AuthenticationManager auth, JwtComponent jwtComponente,
                               UserDetailsService userDetailsService ) {
        super(auth);
        this.jwtComponente = jwtComponente;
        this.userDetailsService = userDetailsService;

    }

    public UsernamePasswordAuthenticationToken pegarAutenticacao(HttpServletRequest request, String token) throws Exception {
        if (!JwtComponent.isTokenValid(token)){
            throw new MensagemTokenInvalido("Token Invalido");
        }

        Claims claims = jwtComponente.getClaims(token);
        //verifica se existe no banco de dados - vem da classe UsuarioLoginService
        UserDetails usuario = userDetailsService.loadUserByUsername(claims.getSubject());
        //null são as credenciais
        return new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
    }


    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        String token = request.getHeader("Authorization");

        if (token != null && token.startsWith("Token ")) {
            try {
                UsernamePasswordAuthenticationToken auth = pegarAutenticacao(request, token.substring(6));
                SecurityContextHolder.getContext().setAuthentication(auth);
            } catch (MensagemTokenInvalido exception) {
                throw new MensagemTokenInvalido("Token Invalido");
            }
        }
        chain.doFilter(request, response);
    }
}
