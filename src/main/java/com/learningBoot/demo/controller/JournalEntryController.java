package com.learningBoot.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.learningBoot.demo.entity.JournalEntry;
import com.learningBoot.demo.service.JournalEntryService;

@RestController
@RequestMapping("/journal")

public class JournalEntryController {
    private interface upInterface {
    int id
    String email 
    }
    @Autowired
    private JournalEntryService journalEntryService;

    private int id=0;
    public HashMap<Integer,JournalEntry> map=new HashMap<>();

    @GetMapping("/get-entry")
    public List<JournalEntry> getAllEntries(){
        return journalEntryService.getEntries();
    }

    @PostMapping("/get-entry")
    public Optional<JournalEntry> getEntry(@RequestBody int id){
        System.out.println("my Id is::::"+id);
        return journalEntryService.findEntry(id);
    }



    @PostMapping("/set-entry/{myid}")
    public void createEntry(@PathVariable int myid,@RequestBody JournalEntry myEntry){
        journalEntryService.saveEntry(myEntry);
    }
    @PutMapping("/update-entry")
    public boolean updateEntry(@RequestBody upInterface p){
        return journalEntryService.updateEntry(p.id, p.email);
        // return true if my entry is succesfully updated or false when my entry is not succesfully updated
    }
    
    
}