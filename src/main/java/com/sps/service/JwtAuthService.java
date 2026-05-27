package com.sps.service;


import com.sps.entity.AuthRequest;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtAuthService {

    private static final long  EXPIRIATION_AT = 1000*60*60;
    @Autowired
    private AuthenticationManager authenticationManager;
    private static final Logger log = LoggerFactory.getLogger(JwtAuthService.class);
    private final String key = "this is the spring practices code base at token generated key";
    private final SecretKey secretKey= Keys.hmacShaKeyFor(key.getBytes()); //Keys.hmacShaKeyFor(key.getBytes());

    public Map<String, String> generateJwtToken(AuthRequest authRequest) {
        try{

            Map<String, String> token = new HashMap<>();
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );

            String generatedToken=getToken(authRequest.getUsername());
            token.put("token", generatedToken);

            return token;
        }catch (Exception e) {
            log.error("An exception thrown when generating the jwt token", e);
            throw e;
        }
    }


    private String getToken(String userName) {
        try{
            return  Jwts.builder().setSubject(userName).
                    setIssuedAt(new Date()).
                    setExpiration(new Date(System.currentTimeMillis() + EXPIRIATION_AT)).
                    signWith(secretKey, SignatureAlgorithm.HS256)
                    .compact();

        }catch (Exception e) {
            throw e;
        }
    }

}
