package com.pracloomcompany.pracloom.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService  {

    private String SECRET_KEY = "5b37a4ee246a2b4e613d1112f8932d88a428825e467e71728191a43e00d6b4b3";

    public String extractCustomerEmail(String jwtToken) {
        System.out.println(isTokenExpired(jwtToken));
        if(isTokenExpired(jwtToken)) return null;
        return  extractClaim(jwtToken,Claims::getSubject); // subject is email for our case

    }

    public <T> T extractClaim(String token , Function<Claims,T> claimsResolver){

       final Claims claims = extractAllClaims(token);
       return claimsResolver.apply(claims);
    }



    private Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    String generateJwtToken(UserDetails customerDetails){
        return generateJwtToken(new HashMap<>(),customerDetails);
    }

    // for us username it is email
    public String generateJwtToken(
        Map<String,Object> extraClaims,
        UserDetails customerDetails
    ){
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(customerDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*60*24))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token , UserDetails customerDetails){
        final String customerEmail = extractCustomerEmail(token);
        return (customerEmail.equals(customerDetails.getUsername()) && !isTokenExpired(token));
        // for us username is email
    }

    public boolean isTokenExpired(String token) {
        Date tokenExpirationDate = extractClaim(token,Claims::getExpiration);
        return tokenExpirationDate.before(new Date());
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
