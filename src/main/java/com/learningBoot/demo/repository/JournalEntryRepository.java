package com.learningBoot.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.learningBoot.demo.entity.JournalEntry;

public interface JournalEntryRepository extends MongoRepository<JournalEntry,Integer> {
    
}
