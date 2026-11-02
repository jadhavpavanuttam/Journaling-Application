package com.example.journalingapp.controller;

import com.example.journalingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.journalingapp.entity.User;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private UserService userService;

    @GetMapping("/health-check")
    public String publicControl() {
        return "HELLO , How can i assist you ?";
    }

    @PostMapping("/create-user")//new User() -->creation is possible and so on.
    public ResponseEntity<?> createUser(@RequestBody User user) {
        userService.saveNewUSer(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
