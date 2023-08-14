
package jwt.example.token.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jwt.example.token.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {
    private static final String SECRET_KEY="12345678";
    private static final int TOKEN_VALIDITY=3600*5;

     public String getUsernameFromToken(String token){
         return getClaimFromToken(token, Claims::getSubject);

     }
     public <T> T getClaimFromToken(String token, Function<Claims,T> claimsResolver){
         final Claims claims =getClaimFromToken(token);
return claimsResolver.apply(claims);

     }
     private Claims getClaimFromToken(String token){
         return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJwt(token).getBody();

     }
     public Boolean validateToken(String token, User user){
//user details dannda
final String username=getUsernameFromToken(token);
return true;
     }
public Boolean iTokenExpired(String token){
         final Date expired=getClaimFromToken(token,Claims::getExpiration);
         return expired.before(new Date());

}
public String generateToken (UserDetails userDetails){
    Map<String,Object> claims=new HashMap<>();
    return Jwts.builder()
            .setClaims(claims)
            .setSubject(userDetails.getUsername())
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis()+TOKEN_VALIDITY *1000))
            .signWith(SignatureAlgorithm.HS512,SECRET_KEY)
            .compact();

}

}
