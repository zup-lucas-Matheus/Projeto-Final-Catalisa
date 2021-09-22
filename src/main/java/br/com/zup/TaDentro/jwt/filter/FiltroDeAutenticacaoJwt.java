package br.com.zup.TaDentro.jwt.filter;


import br.com.zup.TaDentro.jwt.UsuarioLogin;
import br.com.zup.TaDentro.jwt.dto.UsuarioLoginDto;
import br.com.zup.TaDentro.jwt.exeptionAuthentication.AcessoNegadoException;
import br.com.zup.TaDentro.jwt.exeptionAuthentication.MensagemAcessoNegado;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class FiltroDeAutenticacaoJwt extends UsernamePasswordAuthenticationFilter {

    private JwtComponent jwtComponente;
    private AuthenticationManager authenticationManager;

    public FiltroDeAutenticacaoJwt(AuthenticationManager authenticationManager, JwtComponent jwtComponente) {
        this.authenticationManager = authenticationManager;
        this.jwtComponente = jwtComponente;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        ObjectMapper objectMapper = new ObjectMapper();

        try {

            UsuarioLoginDto usuarioLoginDto = objectMapper.readValue(request.getInputStream(), UsuarioLoginDto.class);
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    usuarioLoginDto.getEmail(), usuarioLoginDto.getSenha(), new ArrayList<>()
            );

            Authentication auth = authenticationManager.authenticate(authToken);
            return auth;

        }catch (IOException exception){
            throw new MensagemAcessoNegado("Acesso Negado!");
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException, ServletException {
        UsuarioLogin usuarioLogin = (UsuarioLogin) authResult.getPrincipal();
        String username = usuarioLogin.getUsername();
        int idUsuario = usuarioLogin.getId();

        String token = JwtComponent.gerarToken(username , idUsuario);
        response.setHeader("Access-Control-Expose-Headers", "Authorization");
        response.addHeader("Authorization", "Token "+token);
    }




}
