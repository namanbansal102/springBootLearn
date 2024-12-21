package com.learningBoot.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.learningBoot.demo.entity.JournalEntry;
import com.learningBoot.demo.entity.UserEntry;

public interface JournalEntryRepository extends MongoRepository<JournalEntry,Integer> {
    
    
}
