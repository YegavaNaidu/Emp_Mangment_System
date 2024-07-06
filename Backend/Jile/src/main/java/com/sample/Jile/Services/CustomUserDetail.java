package com.sample.Jile.Services;

import com.sample.Jile.Entity.User;
import com.sample.Jile.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetail implements UserDetailsService {

    @Autowired
    UserRepo userRepo;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {

        return  userRepo.findByUsername(username).orElseThrow(()-> new RuntimeException("user not found !!"));

    }
}
