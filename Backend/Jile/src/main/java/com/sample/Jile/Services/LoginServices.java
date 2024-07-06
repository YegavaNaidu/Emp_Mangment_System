package com.sample.Jile.Services;


import com.sample.Jile.Entity.User;
import com.sample.Jile.Repository.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginServices {

    private Logger logger = LoggerFactory.getLogger(LoginServices.class);
    @Autowired
    UserRepo userRepo;


    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public Optional<User> getUser(String username) {
        return userRepo.findByUsername(username);
    }

    public String verifyCrds(String username ,String password) {
        Optional<User> user = userRepo.findByUsername(username);
        logger.info("user in services" + user.get().getPassword());
        if(bCryptPasswordEncoder.matches(password,user.get().getPassword())){
            return "true";
        }
        return bCryptPasswordEncoder.encode(password);
    }
//        "$2a$10$xGhtWfd4CQklSW2F16Fd2OuyUC.GdJpX1P.8IMtSin3J7o6T9suTe"
    public ResponseEntity<User> register(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return ResponseEntity.ok(userRepo.save(user));
    }
}
