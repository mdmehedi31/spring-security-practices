package com.sps.service;

import com.sps.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    public UserEntity getUserEntityById(Integer userId){
        return this.userRepository.findById(userId).orElse(null);
    }

    public List<UserEntity> getAllUserEntity(){
        return this.userRepository.findAll();
    }

    public UserRegisterResponse registerUser(UserRegisterDTO registerDTO) {

        if(customUserDetailsService.loadUserByUsername(registerDTO.getUsername()) !=null){
            log.info("this is from register user ");
          return  new UserRegisterResponse("User already exists");
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(registerDTO.getUsername());
        userEntity.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        userEntity.setRole(Role.USER);

        userEntity= this.userRepository.save(userEntity);

        return  new UserRegisterResponse(userEntity.getId(),userEntity.getUsername(), userEntity.getRole().name());
    }
}
