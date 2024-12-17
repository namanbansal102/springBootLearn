package com.learningBoot.demo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.TreeUI;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import lombok.Data;
@Document(collection = "user")
@Data
public class UserEntry {
    @Id
    private ObjectId userId;
    @Indexed(unique =true)
    @NonNull
    private String userName;
    @NonNull
    private String passwd;
    @DBRef
    private List<JournalEntry> lst=new ArrayList<>();
}
