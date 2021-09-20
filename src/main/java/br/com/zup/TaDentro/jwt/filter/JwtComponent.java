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
        Date vencimento = new Date(System.currentTimeMillis() + EXPIRATIONTIME);
        return Jwts.builder()
                .setSubject(userName)
                .claim("idUsuario", idUsuario)
                .setExpiration(vencimento)
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

        try {
            Claims claims = getClaims(token);
            String username = claims.getSubject();
            Date vencimento = claims.getExpiration();
            Date dataAtual = new Date(System.currentTimeMillis());

            if (username != null && vencimento != null && dataAtual.before(vencimento)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception exception) {
            return false;

        }
    }

}
