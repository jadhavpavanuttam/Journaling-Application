package com.example.journalingapp.entity;

import jakarta.annotation.Nonnull;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "journal_entry") // row in mysql
@Data  //by default, it will go to ArgsConstructor ,getters,setters and someone Object class methods  .
@NoArgsConstructor
public class JournalEntry {
    @Id  //not compulsory
    private ObjectId id;
    @Nonnull
    private String title;
    @Nonnull
    private String content;
    private LocalDate date;
}
/*
Lombok is the library : is used to reduce boilerplate
getters and setters

@Data : is equivalent to all the following annotations.
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder

    so lombok is registers bytecode :-
 getters(),
 setters(),
 toString(),
 equals(),
 hashCode()
 and added all in the (.class file)
 */
