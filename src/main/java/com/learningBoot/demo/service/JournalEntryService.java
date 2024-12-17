package com.learningBoot.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.learningBoot.demo.entity.JournalEntry;
import com.learningBoot.demo.repository.JournalEntryRepository;

@Component
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    public void saveEntry(JournalEntry journalEntry) {
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getEntries() {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findEntry(int id) {
        System.out.println("Is Found that " + journalEntryRepository.findById(6));
        return journalEntryRepository.findById(id);
    }

    public boolean deleteEntry(int id) {
        if(!journalEntryRepository.findById(id).isPresent()){
            return false;
        }
        journalEntryRepository.deleteById(id);
        return true;
    }
    public boolean updateEntry(int id,String email){
        
        Optional<JournalEntry> temp=journalEntryRepository.findById(id);
        
        if(!temp.isPresent())return false;
        temp.get().setEmail(email);
        journalEntryRepository.save(temp.get());
        return true;
        
    }
}
