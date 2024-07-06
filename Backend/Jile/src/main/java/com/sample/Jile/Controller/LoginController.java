package com.sample.Jile.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.Jile.Entity.Images;
import com.sample.Jile.Entity.JwtRequest;
import com.sample.Jile.Entity.User;
import com.sample.Jile.Services.CustomUserDetail;
import com.sample.Jile.Services.LoginServices;
import com.sample.Jile.security.JWTHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;


@RestController
@RequestMapping("/auth")
//@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    LoginServices loginServices;
    @Autowired
    CustomUserDetail customUserDetail;
    @Autowired
    JWTHelper jwtHelper;
    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/test")
    public ResponseEntity<String> myTest(@RequestBody JwtRequest request){

        logger.info("Username----> " +request.getPassword());
        logger.info("Username----> " + loginServices.verifyCrds(request.getUsername(), request.getPassword()));
        String response=null;
        Authentication authentication = authenticationManager.authenticate
                (new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        if (authentication.isAuthenticated()) {
            logger.info("Authenticated .....");
            response = jwtHelper.generateToken(request.getUsername());
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getuser")
    public ResponseEntity<Optional<User>> getUser(@RequestParam String UserName){
        Optional<User> user =loginServices.getUser(UserName);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping(value = {"/register"},consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<User> CreatUser(@RequestParam("user1") String user1,
                                          @RequestParam("profile_photo")MultipartFile[] file){

        User user = null;
        try {
            user = objectMapper.readValue(user1, User.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println(user.getPassword());
//        try{
//            Set<Images> images = uploadImage(file);
//            user.setEmp_Images(images);
//           return loginServices.register(user);
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//            return null;
//        }

        try {
            Images images = uploadImage(file);
            user.setEmp_Images(images);
            user.setUsername(user.getEmail());
            return  loginServices.register(user);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }


    public Images uploadImage(MultipartFile[] multipartFiles) throws IOException{

        for(MultipartFile file : multipartFiles){
            Images images = new Images(
                    file.getOriginalFilename(),
                    file.getContentType(),
                    file.getBytes()
            );
            System.out.println(file.getOriginalFilename());
            return images;
        }
        return null;
    }


    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }

}
