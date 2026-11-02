package com.example.journalingapp.entity;

import com.fasterxml.jackson.core.io.SegmentedStringWriter;
import jakarta.annotation.Nonnull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "config_journal_app") // row in mysql
@Data  //by default, it will go to ArgsConstructor ,getters,setters and someone Object class methods  .
@NoArgsConstructor
public class ConfigJournalAppEntity {
    private String key;
    private String value;

}
//code flow for the java backend developer : controller --> service --> repository --> D.B