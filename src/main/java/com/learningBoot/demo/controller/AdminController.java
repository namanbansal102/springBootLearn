package com.learningBoot.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learningBoot.demo.entity.UserEntry;
import com.learningBoot.demo.repository.UserEntryRepository;
import com.learningBoot.demo.service.UserEntryService;

@RestController
@RequestMapping("/admin")
public class AdminController {
      @Autowired
    UserEntryService userEntryService;
    @Autowired
    UserEntryRepository userEntryRepository;
    @GetMapping("/list-users")
    public List<UserEntry> listUsers(){
        return userEntryService.listAllUsers();
    }
    @PostMapping("/create-admin-user")
    public ResponseEntity<Boolean> createAdmin(@RequestBody String userName){
        System.out.println("My userName is:::"+userName);
        try{
            Optional<UserEntry> uEntry=userEntryRepository.findByuserName(userName);
            System.out.println("My UEntry is:::"+uEntry);
            if (!uEntry.isPresent()) {
                return new ResponseEntity<>(false,HttpStatus.BAD_REQUEST);
            }
           // uEntry.get().getRoles().add("ADMIN");
            //userEntryRepository.save(uEntry.get());
            return new ResponseEntity<>(true,HttpStatus.OK);
        }
        catch(Exception e){
            System.out.println("Error is:::"+e);
            return new ResponseEntity<>(false,HttpStatus.BAD_GATEWAY);
        }
    }
}
