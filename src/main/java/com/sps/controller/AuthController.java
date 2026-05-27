package com.sps.controller;

import com.sps.entity.AuthRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {


    @PostMapping("/generate")
    public ResponseEntity<Map<String, String>> genereteAuthToken(@RequestBody AuthRequest authRequest){
        Map<String, String> token = new HashMap<String, String>();
        token.put("token","zyg");
        return new ResponseEntity<>(token, HttpStatus.OK);
    }
}
