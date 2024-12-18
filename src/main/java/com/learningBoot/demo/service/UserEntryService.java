package com.learningBoot.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Component;

import com.learningBoot.demo.entity.JournalEntry;
import com.learningBoot.demo.entity.UserEntry;
import com.learningBoot.demo.repository.UserEntryRepository;

@Component
public class UserEntryService {
    @Autowired
    private UserEntryRepository userEntryRepository;
    public boolean createUser(UserEntry userEntry){
        try{

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
        Optional<UserEntry> uEntry=findByUserName(userName);
        System.out.println("My Password is::::"+uEntry.get().getPasswd());
        if (!uEntry.isPresent())return false;
        uEntry.get().setPasswd(passwd);
        userEntryRepository.save(uEntry.get());// for saving my userEntry 
        return true;
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
