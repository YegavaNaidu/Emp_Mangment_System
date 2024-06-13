package com.sample.Jile.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.Jile.Entity.Images;
import com.sample.Jile.Entity.User;
import com.sample.Jile.Services.LoginServices;
import lombok.SneakyThrows;
import org.apache.tomcat.util.http.parser.MediaTypeCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    LoginServices loginServices;

    @GetMapping("/test")
    public String myTest(@RequestParam String UserName,@RequestParam String Password){
        String result = loginServices.verifyCrds(UserName,Password);
        return result;
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
            return  loginServices.register(user);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

//    public Set<Images> uploadImage(MultipartFile[] multipartFiles) throws IOException {
//        Set<Images> imagefiles = new HashSet<>();
//
//        for(MultipartFile file : multipartFiles){
//            Images image = new Images(
//                    file.getOriginalFilename(),
//                    file.getContentType(),
//                    file.getBytes()
//            );
//            imagefiles.add(image);
//        }
//
//        return imagefiles;
//    }


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

}
