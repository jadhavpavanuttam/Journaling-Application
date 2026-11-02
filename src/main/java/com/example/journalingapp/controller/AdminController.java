package com.example.journalingapp.controller;

import com.example.journalingapp.entity.User;
import com.example.journalingapp.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all-users")//GET : localhost:8080/abc/admin/all-users
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> all = userService.getAll();
        if (all != null && !all.isEmpty())
            return new ResponseEntity<>(all, HttpStatus.OK);//200
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); //404
    }

    @PostMapping("/create-admin-user")//POST : localhost:8080/abc/admin/create-admin-user
    public ResponseEntity<User> createAdmin(@RequestBody User user) {
        userService.saveAdmin(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    //gives the new user admin priority iff it has a admin role.
}
/**
 * DTO : stands for Data Transfer Objects.
 * used to constant fields/ make as POJO.
 * Role Based Authentication -->
 * an admin which will give the priority as admin and authenticated it .
 * for the admin role .
 * JWT is a compact , URL-safe token that
 * can carry information
 * between parties.
 * <p>
 * Header ,Payload , Signature
 * <p>
 * Class Path --> it is the way to achieve the jvm , is to defined by jvm provide (byte,jars,configuration )
 */
