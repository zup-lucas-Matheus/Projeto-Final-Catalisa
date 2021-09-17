package br.com.zup.TaDentro.jwt.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtComponent {

    private static final long EXPIRATIONTIME = 864000000;
    private static final String CHAVE = "xablau";



}
