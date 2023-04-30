package com.hari.netflix.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.NoArgsConstructor;
import org.springframework.core.codec.Decoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.security.KeyPair;
import java.util.Date;
import java.util.Map;

@Service
@NoArgsConstructor
public class JWTService {
    private static String SIGNING_KEY = "34753778214125432A462D4A614E645267556B58703273357638792F423F4528";
    private static final KeyPair keyPair = Keys.keyPairFor(SignatureAlgorithm.RS512);
    public static String getEmail(String jwt) {
        return extractClaims(jwt).getSubject();
    }

    public static String generateJWT(Map<String, Object> claims, UserDetails userDetails) {
        return Jwts.builder()
//                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000))
                .signWith(keyPair.getPrivate())
                .compact();
    }

    public static Boolean isValidToken(String jwt, UserDetails userDetails) {
        String email = getEmail(jwt);
        return (email.equals(userDetails.getUsername()) && !hasExpired(jwt));
    }

    private static Boolean hasExpired(String jwt) {
        return (new Date()).after(extractClaims(jwt).getExpiration());
    }

    private static Claims extractClaims(String jwt) {
        return Jwts
                .parserBuilder()
                .setSigningKey(keyPair.getPublic())
                .build()
                .parseClaimsJws(jwt)
                .getBody();
    }

    private Key getSigningKey() {
        byte[] signingKey = Decoders.BASE64.decode(SIGNING_KEY);
        return Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }
}
