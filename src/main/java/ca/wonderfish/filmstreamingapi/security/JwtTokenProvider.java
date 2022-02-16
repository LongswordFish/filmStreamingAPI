package ca.wonderfish.filmstreamingapi.security;

import ca.wonderfish.filmstreamingapi.domains.User;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;
import org.springframework.security.core.Authentication;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static ca.wonderfish.filmstreamingapi.security.SecurityConstant.EXPIRATION_TIME;
import static ca.wonderfish.filmstreamingapi.security.SecurityConstant.SECRET;

@Component
public class JwtTokenProvider {

    //generate the token
    public String generateToken(Authentication auth){
        User user = (User)auth.getPrincipal();
        Date now = new Date(System.currentTimeMillis());

        Date expireDate = new Date(now.getTime()+EXPIRATION_TIME);

        String userId = Long.toString(user.getId());

        Map<String,Object> claims = new HashMap<>();
        claims.put("id",userId);
        claims.put("username",user.getUsername());
        claims.put("fullName",user.getFullName());


        return Jwts.builder()
                .setSubject(userId)
                .setClaims(claims)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512,SECRET)
                .compact();
    }

    //generate the token
    public String generateTokenForUser(User user){

        Date now = new Date(System.currentTimeMillis());

        Date expireDate = new Date(now.getTime()+EXPIRATION_TIME);

        String userId = Long.toString(user.getId());

        Map<String,Object> claims = new HashMap<>();
        claims.put("id",userId);
        claims.put("username",user.getUsername());
        claims.put("fullName",user.getFullName());


        return Jwts.builder()
                .setSubject(userId)
                .setClaims(claims)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512,SECRET)
                .compact();
    }

    //validate the token
    public boolean validateToken(String token){
        try{
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
            return true;
        }catch(SignatureException e){
            System.out.println("Invalid JWT Signature");
        }catch(MalformedJwtException e){
            System.out.println("Invalid JWT token");
        }catch(ExpiredJwtException e){
            System.out.println("JWT token expired");
        }catch(UnsupportedJwtException e){
            System.out.println("UnsupportedJwtException");
        }catch(IllegalArgumentException e){
            System.out.println("JWT claims is empty");
        }
        return false;
    }

    //get user id from token
    public Long getUserIdFromJWT(String token){
        Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        String id = (String)claims.get("id");
        return Long.parseLong(id);
        //return (Long)claims.get("id");
    }
}
