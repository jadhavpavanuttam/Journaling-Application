package com.example.journalingapp.repository;

import com.example.journalingapp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigJournalAppRepo extends MongoRepository<JournalEntry, ObjectId> {

}
//project sequence : controller -->service -->repositor
