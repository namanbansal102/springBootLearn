package com.learningBoot.demo.controller;

import java.net.http.HttpClient;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learningBoot.demo.entity.UserEntry;
import com.learningBoot.demo.repository.UserEntryRepository;
import com.learningBoot.demo.service.UserEntryService;
@RestController
@RequestMapping("/users")
public class UserEntryController {
    @Autowired
    UserEntryService userEntryService;

    @GetMapping("/list-users")
    public List<UserEntry> listUsers(){
        return userEntryService.listAllUsers();
    }
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
    @Transactional
    @PutMapping("/update-user")
        public ResponseEntity<Boolean> updateUser(@RequestBody UserEntry u){
            try{
                boolean is=userEntryService.updateEntry(u.getUserName(), u.getPasswd());
                if(is){
                    return new ResponseEntity<>(true,HttpStatus.ACCEPTED);
                }
                else{
                    return new ResponseEntity<>(true,HttpStatus.UNAUTHORIZED);
                }
            }
            catch(Exception e){
                
            }
            return new ResponseEntity<>(false,HttpStatus.BAD_GATEWAY);
        }

    @DeleteMapping("/delete-user")
    public ResponseEntity<Boolean> deleteUser(@RequestBody String uName){
        try{
            boolean is=userEntryService.deleteEntry(uName);
            if(is){
                return new ResponseEntity<>(true,HttpStatus.ACCEPTED);
            }
            else{
                return new ResponseEntity<>(true,HttpStatus.UNAUTHORIZED);
            }
        }
        catch(Exception e){
        }
        return new ResponseEntity<>(false,HttpStatus.BAD_GATEWAY);
    }
    
}
