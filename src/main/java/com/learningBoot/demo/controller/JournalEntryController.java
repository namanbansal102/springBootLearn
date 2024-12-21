package com.learningBoot.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.learningBoot.demo.entity.JournalEntry;
import com.learningBoot.demo.entity.UserEntry;
import com.learningBoot.demo.service.JournalEntryService;
import com.learningBoot.demo.service.UserEntryService;

@RestController
@RequestMapping("/journal")

public class JournalEntryController {
    public interface upInterface {
    int id=-1;
    String email="";
    }
    @Autowired
    private JournalEntryService journalEntryService;

    private int id=0;
    public HashMap<Integer,JournalEntry> map=new HashMap<>();

    @Autowired
    UserEntryService userEntryService;
    @GetMapping("/get-entry")
    public ResponseEntity<Optional<List<JournalEntry>>> getAllEntries(){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        System.out.println("My UserName is:::::::"+userName);
       Optional<UserEntry> uEntry= userEntryService.findByUserName(userName);
       if (uEntry.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
       }
      List<JournalEntry> lst=uEntry.get().getLst();
       System.out.println("my List is :::"+lst);
       if (lst.size()==0) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
       }
       
        return new ResponseEntity<>(Optional.of(lst),HttpStatus.OK);
    }

  


    @PostMapping("/set-entry")
    public ResponseEntity<Boolean> createEntry(@RequestBody JournalEntry myEntry){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();   
        Optional<UserEntry> uEntry= userEntryService.findByUserName(userName);
        if (uEntry.isEmpty()) {
             return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        int id=journalEntryService.saveEntry(myEntry);
        // saving my entry in my user Proile
       uEntry.get().getLst().add(myEntry);
        System.out.println("My UEntry lst is::::"+uEntry.get().getLst());
        boolean is=userEntryService.createUser(uEntry.get());
        if (is) {
            return  new ResponseEntity<>(true,HttpStatus.OK);
        }
        return new ResponseEntity<>(false,HttpStatus.BAD_GATEWAY);
     }
    @PutMapping("/update-entry/{userName}")
    public ResponseEntity<?> updateEntry(@PathVariable String userName,@RequestBody JournalEntry p){
        boolean st=journalEntryService.updateEntry(p.getId(), p.getArticle());
        if(st){
            return new ResponseEntity<>(true,HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(false,HttpStatus.NOT_FOUND);
        // return true if my entry is succesfully updated or false when my entry is not succesfully updated
    }
    @Transactional
    @DeleteMapping("/delete-entry/{userName}")
    public ResponseEntity<Boolean> deleteEntry(@PathVariable String userName,@RequestBody int id){
        JournalEntry jEntry=journalEntryService.deleteEntry(id);
        boolean is=userEntryService.deleteJournalFromUser(userName,null);
        if(is)return new ResponseEntity<>(HttpStatus.OK);
        //throw new Exception("Error Getting Error ")
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }
    
    
}
