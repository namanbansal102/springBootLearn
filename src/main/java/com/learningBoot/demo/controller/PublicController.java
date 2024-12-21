package com.learningBoot.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learningBoot.demo.entity.UserEntry;
import com.learningBoot.demo.service.UserEntryService;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    UserEntryService userEntryService;

     @PostMapping("/create-user")
    public ResponseEntity<Boolean> createUser(@RequestBody UserEntry userEntry){
        System.out.println("user is Created");
        boolean isCreated=userEntryService.createUser(userEntry);
        try{

            if (isCreated) {
                return new ResponseEntity<>(true,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(false,HttpStatus.BAD_REQUEST);
        }
    }
    catch(Exception e){
        System.out.println("Exception is:::"+e);
        return new ResponseEntity<>(false,HttpStatus.BAD_GATEWAY);
    }
        
    }
    
}
