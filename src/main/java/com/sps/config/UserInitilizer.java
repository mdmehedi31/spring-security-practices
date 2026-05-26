package com.sps.config;


import com.sps.entity.UserEntity;
import com.sps.entity.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserInitilizer {

    private static final Logger log = LoggerFactory.getLogger(UserInitilizer.class);

    @Bean
    public CommandLineRunner addUser(UserRepository userRepository, PasswordEncoder passwordEncoder) {

            return args -> {
                List<UserEntity> users = new ArrayList<UserEntity>();

                if (!userRepository.findByUserName("admin").isPresent()) {
                    UserEntity user = new UserEntity();
                    user.setUserName("admin");
                    user.setPassword(passwordEncoder.encode("admin123"));
                    user.setUserAddress("2/a Dhaka");
                    user.setAge(28);
                    user.setPhone("098765567789");
                    user.setEmail("admin@gmail.com");
                    user.setGender("male");
                    user.setRole("ROLE_ADMIN");
                    users.add(user);
                }

                if (!userRepository.findByUserName("user").isPresent()) {
                    UserEntity user = new UserEntity();
                    user.setUserName("user");
                    user.setPassword(passwordEncoder.encode("user123"));
                    user.setUserAddress("2/a Chittagong");
                    user.setAge(30);
                    user.setPhone("01985754422");
                    user.setEmail("user@gmail.com");
                    user.setGender("male");
                    user.setRole("ROLE_USER");
                    users.add(user);
                }

                if (users != null && users.size() > 0) {
                    userRepository.saveAll(users);
                }
            };
    }

}
