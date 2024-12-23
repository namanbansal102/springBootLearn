package com.learningBoot.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learningBoot.demo.entity.UserEntry;
import com.learningBoot.demo.service.UserEntryService;

@RestController
@RequestMapping("/admin")
public class AdminController {
      @Autowired
    UserEntryService userEntryService;
    @GetMapping("/list-users")
    public List<UserEntry> listUsers(){
        return userEntryService.listAllUsers();
    }
}
