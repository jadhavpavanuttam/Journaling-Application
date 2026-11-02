package com.example.journalingapp.service;

import com.example.journalingapp.entity.JournalEntry;
import com.example.journalingapp.entity.User;
import com.example.journalingapp.repository.JournalEntryRepo;
import org.bson.types.ObjectId;
import org.slf4j.Logger;  //logging abstractoion framework
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {
    @Autowired
    private JournalEntryRepo journalEntryRepo;  //variable dependency injection
    @Autowired
    private UserService userService;


    @Transactional //get method to allocate as single
    public boolean saveEntry(JournalEntry journalEntry, String username) {
        JournalEntry saved;
        try {
            User user = userService.findByUsername(username);
            journalEntry.setDate(LocalDate.now());//  saved at here localdate
            saved = journalEntryRepo.save(journalEntry);
            user.getJournalEntries().add(saved);
            userService.saveUser(user);
        } catch (Exception ex) {
            throw new RuntimeException(ex.getCause());
        }
        return true;
    }

    public void saveEntry(JournalEntry journalEntry) {
        User user;
        journalEntryRepo.save(journalEntry);
    }

    public List<JournalEntry> getAll() {
        return journalEntryRepo.findAll();
    }

    public Optional<JournalEntry> findEntryById(ObjectId id) {
        return journalEntryRepo.findById(id);
    }

    @Transactional
    public boolean deleteEntryById(ObjectId id, String username) {
        boolean removed = false;
        try {
            User user = userService.findByUsername(username);
            removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            if (removed) {
                userService.saveUser(user);// if not properly work change it into saveNewUser(user);
                journalEntryRepo.deleteById(id);

            }
        } catch (Exception ex) {
            throw new RuntimeException("An error Occured,when deleting the entry.." + ex);
        }
        return removed;
    }

    public List<JournalEntry> findByUsername(String username) {
        return null;
    }
}

/*
controller --> service --> repository

replication :- same data copy on another server is known as replication.
shading :- our data is stored on destributed manner is known as shading

 @Transactional  //provide the properties like all or none -->
 like in the dbms all otherwise rollback...
 applying the peroperties on saveEntry(JournalEntry journalEntry, String userName) method

 Slf4j --> stands for simple logging fassat for java.
 this is an abestraction framework. to implement using logback by default.

 */
