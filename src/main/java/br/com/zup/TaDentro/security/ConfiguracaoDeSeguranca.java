package br.com.zup.TaDentro.security;

import br.com.zup.TaDentro.jwt.filter.FiltroDeAutenticacaoJwt;
import br.com.zup.TaDentro.jwt.filter.JwtComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class ConfiguracaoDeSeguranca extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtComponent jwtComponent;

    private static final String[] POST_PUBLICOS = {
            "/usuarios",
            "/login"
    };

    //Metodo que configura toda nossa aplicação
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.cors().configurationSource(configuracaoDeCors());
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, POST_PUBLICOS).permitAll()
                .anyRequest().authenticated();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilter(new FiltroDeAutenticacaoJwt(authenticationManager(), jwtComponent));

    }

    //Metodo que configura o CORS.
    @Bean
    protected CorsConfigurationSource configuracaoDeCors(){

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }


}
