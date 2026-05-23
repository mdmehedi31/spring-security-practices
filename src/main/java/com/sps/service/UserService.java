package com.sps.service;

import com.sps.entity.UserEntity;
import com.sps.entity.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService{


    @Autowired
    private UserRepository userRepository;


    public UserEntity getUserEntityById(Integer userId){
        return this.userRepository.findById(userId).orElse(null);
    }
}
