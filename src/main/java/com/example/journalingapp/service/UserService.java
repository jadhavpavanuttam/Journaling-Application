package com.example.journalingapp.service;

import com.example.journalingapp.controller.JournalEntryController;
import com.example.journalingapp.entity.User;
import com.example.journalingapp.repository.UserRepository;
import jakarta.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j  // just like a lombok no need to create object explicitly.
public class UserService {
//    Logger logger = LoggerFactory.getLogger(JournalEntryController.class);

    @Autowired
    public UserRepository userRepository;
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public boolean saveNewUSer(@Nonnull User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            userRepository.save(user);
            return true;
        } catch (Exception e) {
//            throw error in case of same user creating again and again.
            log.error("Error occured for user : {} ,  password : {} ", user.getUsername(), user.getPassword(), e);
            log.warn("hahahahaahahah");
            log.info("nononono", e.getMessage());
            log.debug("name ", e.getMessage());
            if (log.isTraceEnabled())
                log.trace("TRACING IS ENABLES : ", e.getMessage());
            return false;
        }
    }

    public void saveAdmin(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER", "ADMIN"));
        userRepository.save(user);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public Optional<User> findEntryById(ObjectId id) {
        return userRepository.findById(id);
    }

    public void deleteEntryById(ObjectId id) {

        userRepository.deleteById(id);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
/*
can we used also in case of logging to implement it --> in the format of .yml
logging
  level
    net
      engineering-digest
        journalAp DEBUG

 controller --> service --> repository -->db
    hJL3dtMT2cGhAH7u
    pavan@1234
    KtqwMFqisNXFTarV
 */