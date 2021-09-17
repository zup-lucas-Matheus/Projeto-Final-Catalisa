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


    public static String gerarToken(String userName, int idUsuario){

        return Jwts.builder()
                .setSubject(userName)
                .claim("idUsuario", idUsuario)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS512, CHAVE)
                .compact();

    }

    public static Claims getClaims(String token) throws Exception {

        try {
            return Jwts.parser()
                    .setSigningKey(CHAVE)
                    .parseClaimsJws(token).getBody();

        }catch (Exception exception){
            throw new Exception("Token Invalído");
        }

    }

    public static boolean isTokenValid(String token) {


}
