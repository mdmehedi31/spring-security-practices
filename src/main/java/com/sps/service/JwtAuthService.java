package com.sps.service;


import com.sps.entity.AuthRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class JwtAuthService {

    @Autowired
    private AuthenticationManager authenticationManager;
    private static final Logger log = LoggerFactory.getLogger(JwtAuthService.class);

    public Map<String, String> generateJwtToken(AuthRequest authRequest) {
        try{

            Map<String, String> token = new HashMap<>();
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );

            token.put("token", "token is");

            return token;
        }catch (Exception e) {
            log.error("An exception thrown when generating the jwt token", e);
            throw e;
        }
    }

}
