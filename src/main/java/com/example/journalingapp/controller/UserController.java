package com.example.journalingapp.controller;

import com.example.journalingapp.entity.User;
import com.example.journalingapp.repository.UserRepository;
import com.example.journalingapp.service.UserService;
import com.sun.net.httpserver.HttpsConfigurator;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping("/user-entry")   //used to request the postman / embedded server
public class UserController {
    @Autowired
    public UserService userService;
    @Autowired
    public UserRepository userRepository;

    @PutMapping //update
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();   //only Authenticated person can till their
        String name = authentication.getName();
        AtomicReference<User> userInDb;
        userInDb = new AtomicReference<>(userService.findByUsername(name));
        if (userInDb.get() != null) {
            userInDb.get().setUsername(user.getUsername());
            userInDb.get().setPassword(user.getPassword());
            userService.saveNewUSer(userInDb.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }//RESET_CONTENT --> Ok

    @DeleteMapping("name/{name}") //delete
    public ResponseEntity<?> deleteMapping(@PathVariable String name) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        userRepository.deleteByUsername(authentication.getName());
        User byUsername = userService.findByUsername(authentication.getName());
        if (byUsername.getUsername().equals(name)) {
            userRepository.deleteByUsername(name);
            return new ResponseEntity<>(HttpStatus.GONE);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
/*
Elevenlabs --> Text to Speach converter website

an api is used to automate the (cheez) objects or anythings.
Dubbing mp4 to mp3 converter into
pipe-dream is one of the tool which is to used automate the the dubbing.

trigger --> flow of processes pipe-dream process.

ChatGpt --> Download through PipeDream


 404 NOT FOUND
@PutMapping:- http:verb+url =>rest API's
External API Integration :-
get() / post() we have to integrate without heating a postman
is known as external api integration.
DeSerialization -->
The process of contverting a json into java object is known as De-serializatioin.

   {
        "id": "6aa0116c5f4a30e69a5c0509",
        "actor": "pavan",
        "action": "LOGIN",
        "resource": "user:6a9f73c34922d64cddff3305",
        "timestamp": "2026-09-08T13:45:16.055Z",
        "metadata": {}
    }

    --> java pojo :-

import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
ObjectMapper om = new ObjectMapper();
Root root = om.readValue(myJsonString, Root.class);
public class Metadata{
}
public class Root{
    public String id;
    public String actor;
    public String action;
    public String resource;
    @JsonProperty("time_stamp")  --> converting snack case into camel case notation.
    public Date timeStamp;
    public Metadata metadata;
}
custom class
{
private final String apiKey="b4aed23dccQEwc3d390";
private final String API="http://api.weatherstack.com/current?access_key=API";
public String getWeather(String city)
{
String final=API.replace("CITY",city).replace("API_KEY" ,apiKey);
restTemplate.exchange(final,httpMethod.GET,null,WeatherResponce.class);

}
}

whenever we want to used the postmapping a user without using postman. in that case we will used.

User user = User.builder().username("pavan").password("pavan").build();
HttpEntity<User> entity=new HttpEntity<>(user);
ResponceEntyty<User> body=responceTemplate.exchange(fial,HttpMethod.POST,httpEntity,wetherResponse.class);

microservice :-
collections of different projects as to deploy on different ports.

text to voice converter ElevenLabs.
 import
 { ElevenLabsClient }
from
"@elevenlabs/elevenlabs-js";
const elevenlabs= new ElevenLabsClient();
const audio= await elevenlabs
  .textToSpeech.convert("NOpBlnGInO9m6vDvFkFC Spuds Oxley", {text:"
In the ancient land of Eldoria, where skies shimmered and forests, whispered secrets to the wind, lived a dragon named Zephyros. [sarcastically] Not the “burn it all down” kind... [giggles] but he was gentle, wise, with eyes like old stars. [whispers] Even the birds fell silent when he passed.",
modelId:
"eleven_v3",languageCode:"en
English",});

 */