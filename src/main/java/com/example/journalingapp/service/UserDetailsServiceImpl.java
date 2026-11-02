package com.example.journalingapp.service;

import com.example.journalingapp.entity.User;
import com.example.journalingapp.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository repository;    //we have to mock this dependency means we have to make a fack repository

    public UserDetailsServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = Optional
                .of(repository.findByUsername(username))
                .orElseThrow(() ->
                        new UsernameNotFoundException("User is not found... " + username));
        return org.springframework.security.core.userdetails
                .User
                .builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRoles().toArray(new String[0]))//new String[0] is used to recized conversion on the particular data types
                .build();
    }
}
/*
Mocking:- we will be make a fack repository and test our application with the help of dummy
user in this way our time is reduces to get fetching data from the actual database is known as
mocking we will achiced this one with the help of MOCKYTOCKY.

process of to making a fack repository :-
Mock :- mean we want to make a fack repository & test out application with the help

 */