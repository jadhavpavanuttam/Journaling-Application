package com.example.journalingapp.repository;

import com.example.journalingapp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.annotation.Id;

@Repository
public interface JournalEntryRepo extends MongoRepository<JournalEntry, ObjectId> {

}
//project sequence : controller -->service -->repositor
