package com.example.journalingapp.entity;

import jakarta.annotation.Nonnull;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "user_entries") // row in mysql
@Data  //generate automatically getters and setters
@Builder  // opposite of --> @NoArgsConstructor because of in concept of testing we will be used builder()
public class User {
    @Id
    private ObjectId id;
    @Indexed(unique = true)  //username will be unique
    @Nonnull
    private String username;
    @Nonnull
    private String password;
    @DBRef  //is used to give reference of JournalEntry from DB.
    public List<JournalEntry> journalEntries = new ArrayList<>();
    private List<String> roles;//user entity
}
/*
transaction :- is used to define the properties of ACID.
*/