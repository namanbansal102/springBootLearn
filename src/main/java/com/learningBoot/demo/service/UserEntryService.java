package com.learningBoot.demo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.management.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.learningBoot.demo.entity.JournalEntry;
import com.learningBoot.demo.entity.UserEntry;
import com.learningBoot.demo.repository.UserEntryRepository;

@Component
public class UserEntryService {
    private static final PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
    @Autowired
    private UserEntryRepository userEntryRepository;
    public boolean createUser(UserEntry userEntry){
        try{
            System.out.println("Running createUser Function");
            userEntry.setPasswd(passwordEncoder.encode(userEntry.getPasswd()));
            userEntry.setRoles(Arrays.asList("USER"));
            userEntryRepository.save(userEntry);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
    public   List<UserEntry> listAllUsers(){
        return userEntryRepository.findAll();
    }
    public Optional<UserEntry> findByUserName(String uName){

        
        return userEntryRepository.findByuserName(uName); 
    }
    public boolean deleteEntry(String userName){
        Optional<UserEntry> uEntry=findByUserName(userName);
        try{
            userEntryRepository.deleteById(uEntry.get().getUserId());
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
    public boolean updateEntry(String userName,String passwd){
        try{

            Optional<UserEntry> uEntry=userEntryRepository.findByuserName(userName);
            System.out.println("My Password is::::"+uEntry.get().getPasswd());
            if (!uEntry.isPresent())return false;
            uEntry.get().setPasswd(passwordEncoder.encode(passwd));
           userEntryRepository.save(uEntry.get());// for saving my userEntry 
            return true;
        }
        catch(Exception e){ 
            System.out.println("Exception is:::"+e);
            return false;
        }
    }
    public boolean deleteJournalFromUser(String userName,JournalEntry jEntry){
        try{
            Optional<UserEntry> userEntry=findByUserName(userName);
            List<JournalEntry> lst=userEntry.get().getLst(); 
            userEntry.get().getLst().removeIf(x->x.getId()==jEntry.getId());
            userEntryRepository.save(userEntry.get());  
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
}
