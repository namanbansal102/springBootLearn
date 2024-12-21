package com.learningBoot.demo.repository;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.learningBoot.demo.entity.UserEntry;

public interface UserEntryRepository  extends MongoRepository<UserEntry,ObjectId>{
    @Query("{ 'userName' : ?0 }")
    Optional<UserEntry> findByuserName(String userName);
}