package com.learningBoot.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.learningBoot.demo.entity.JournalEntry;
import com.learningBoot.demo.entity.UserEntry;
import com.learningBoot.demo.repository.JournalEntryRepository;

@Component
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    public int saveEntry(JournalEntry journalEntry) {
        journalEntryRepository.save(journalEntry);
        return journalEntry.getId();
    }

    public List<JournalEntry> getEntries() {
        return journalEntryRepository.findAll();
    }


    public Optional<JournalEntry> findEntry(int id) {
        System.out.println("Is Found that " + journalEntryRepository.findById(6));
        return journalEntryRepository.findById(id);
    }

    public JournalEntry deleteEntry(int id) {
        if(!journalEntryRepository.findById(id).isPresent()){
            return null;
        }
        JournalEntry jEntry=journalEntryRepository.findById(id).get();
        journalEntryRepository.deleteById(id);
        return jEntry;
    }
    public boolean updateEntry(int id,String content){
        
        Optional<JournalEntry> temp=journalEntryRepository.findById(id);
        
        if(!temp.isPresent())return false;
        temp.get().setArticle(content);
        journalEntryRepository.save(temp.get());
        return true;
        
    }
}
