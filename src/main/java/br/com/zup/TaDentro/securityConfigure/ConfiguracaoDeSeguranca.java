package br.com.zup.TaDentro.securityConfigure;

import br.com.zup.TaDentro.jwt.filter.FiltroDeAutenticacaoJwt;
import br.com.zup.TaDentro.jwt.filter.FiltroDeAutorizacao;
import br.com.zup.TaDentro.jwt.filter.JwtComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class ConfiguracaoDeSeguranca extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtComponent jwtComponent;
    @Autowired
    private UserDetailsService userDetailsService;

    private static final String[] POST_PUBLICOS = {
            "/usuarios",
            "/login"
    };

    private static final String[] GET_PUBLICOS = {
        "/usuarios",
        "/indicacao"

    };


    //Metodo que configura toda nossa aplicação
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.cors().configurationSource(configuracaoDeCors());
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, POST_PUBLICOS).permitAll()
                .antMatchers(HttpMethod.GET, GET_PUBLICOS).permitAll()
                .anyRequest().authenticated();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilter(new FiltroDeAutenticacaoJwt(authenticationManager(), jwtComponent));
        http.addFilter(new FiltroDeAutorizacao(authenticationManager(), jwtComponent, userDetailsService));

    }

    //Metodo que configura o CORS.
    @Bean
    protected CorsConfigurationSource configuracaoDeCors(){

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Bean //gerador de cryptografia - deve ser sempre o mesmo, por isso utiliza-se o bean
    BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
