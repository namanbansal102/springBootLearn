package com.learningBoot.demo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document
@Data
public class JournalEntry {
    @Id
    private int id;
    private String name;
    private String article;
    private String email;
}
