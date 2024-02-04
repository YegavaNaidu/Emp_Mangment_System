package com.sample.Jile.Services;

import com.sample.Jile.Entity.User;
import com.sample.Jile.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServices {


    @Autowired
    UserRepo userRepo;

    public String verifyCrds(String userName, String password) {
        return "0";
    }

    public User register(User user) {
        return userRepo.saveAndFlush(user);
    }
}
