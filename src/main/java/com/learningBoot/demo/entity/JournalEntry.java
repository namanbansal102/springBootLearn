package com.learningBoot.demo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class JournalEntry {
    @Id
    private int id;
    private String name;
    private String article;
    private String email;
    public void setId(int id){
        this.id=id;
    }
    public int getId(){
        return this.id;
    }
    public void setName(String name){
        this.name=name;
    }
    public void setArticle(String article){
        this.article=article;
    }
    public void setEmail(String email){
        this.email=email;
    }
    public String getName(){
        return this.name;
    }
    public String getArticle(){
        return this.article;
    }
    public String getEmail(){
        return this.email;
    }
}
