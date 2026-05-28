package com.sps.controller;

import com.sps.entity.AuthRequest;
import com.sps.service.JWTUtils;
import com.sps.service.JwtAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtAuthService jwtAuthService;
    @PostMapping("/generate")
    public ResponseEntity<Map<String, String>> genereteAuthToken(@RequestBody AuthRequest authRequest){
        Map<String, String> token = jwtAuthService.getJwtToken(authRequest);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }
}
