package com.sample.Jile.Controller;

import com.sample.Jile.Entity.User;
import com.sample.Jile.Services.LoginServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class LoginController {

    @Autowired
    LoginServices loginServices;

    @GetMapping("/test")
    public String myTest(@RequestParam String UserName,@RequestParam String Password){
        String result = loginServices.verifyCrds(UserName,Password);
        return result;
    }

    @PostMapping("/register")
    public User CreatUser(@RequestBody User user){
        return loginServices.register(user);
    }

}
