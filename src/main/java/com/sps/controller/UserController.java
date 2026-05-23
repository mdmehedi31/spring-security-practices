package com.sps.controller;


import com.sps.entity.UserEntity;
import com.sps.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody UserEntity user) {
        return null;
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable Integer userId  ) {
        UserEntity userEntity = this.userService.getUserEntityById(userId);
        return ResponseEntity.ok(userEntity);
    }
}
