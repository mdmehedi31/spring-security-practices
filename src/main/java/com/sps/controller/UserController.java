package com.sps.controller;


import com.sps.entity.UserEntity;
import com.sps.entity.UserRegisterDTO;
import com.sps.entity.UserRegisterResponse;
import com.sps.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<UserRegisterResponse> createUser(@RequestBody UserRegisterDTO user) {
        UserRegisterResponse userRegisterResponse  =userService.registerUser(user);
        return new ResponseEntity<>(userRegisterResponse, HttpStatus.CREATED);
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable Integer userId  ) {
        System.out.println("Get user controller ... ... ...");
        UserEntity userEntity = this.userService.getUserEntityById(userId);
        return ResponseEntity.ok(userEntity);
    }


    @GetMapping("/gets")
    public ResponseEntity<List<UserEntity>> getUsers() {
        System.out.println("Get users controllers");
        List<UserEntity> users = this.userService.getAllUserEntity();
        return ResponseEntity.ok(users);
    }
}
