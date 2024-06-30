package com.sample.Jile.Services;

import com.sample.Jile.Entity.User;
import com.sample.Jile.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LoginServices {


    @Autowired
    UserRepo userRepo;

    public String verifyCrds(String userName, String password) {
        return (userName.equals(password)) ? "logged in" : "incorrect crds";
    }

    public ResponseEntity<User> register(User user) {
        return ResponseEntity.ok(userRepo.save(user));
    }
}
