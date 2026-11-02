package com.example.journalingapp.controller;

import com.example.journalingapp.entity.JournalEntry;
import com.example.journalingapp.entity.User;
import com.example.journalingapp.service.JournalEntryService;
import com.example.journalingapp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal-entry")   //used to request the postman / embedded server
public class JournalEntryController {
    private JournalEntryService journalEntryService;

    private UserService userService;

    public JournalEntryController(UserService userService, JournalEntryService journalEntryService) {
        this.userService = userService;
        this.journalEntryService = journalEntryService;
    }

    @GetMapping
    public ResponseEntity<List<JournalEntry>> getAllJournalEntriesOfUser() { //localhost:8080/journal GET
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();
        String name = authentication.getName();
        User user = userService.findByUsername(name); //we will be
        List<JournalEntry> all = user.getJournalEntries();
        if (!all.equals("") && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }//Ok

    @PostMapping//pathvariable :  postman request catchup in the JSON format and get.
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry journalEntry) { //localhost:8080/journal POST
        try {
            Authentication authentication = SecurityContextHolder
                    .getContext()
                    .getAuthentication();
            String name = authentication.getName();
            journalEntryService.saveEntry(journalEntry, name);
            return new ResponseEntity<>(journalEntry, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }//Ok

    @GetMapping("id/{myId}")//findById
    public ResponseEntity<Object> getJournalEntryById(@PathVariable ObjectId myId) {
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();
        String name = authentication.getName();
        User user = userService.findByUsername(name);


        List<JournalEntry> collect = user
                .getJournalEntries()
                .stream()
                .filter(x -> x.getId().equals(myId))
                .collect(Collectors.toList());
        if (!collect.isEmpty()) {
            Optional<JournalEntry> entryById = journalEntryService
                    .findEntryById(myId);
            if (entryById.isPresent())
                return new ResponseEntity(entryById.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/id/{myId}") //localhost:8080/journal-entry/id/DBRef[journal_id]
    public ResponseEntity<?> deleteEntry(@PathVariable ObjectId myId) {
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();
        String name = authentication.getName();
        boolean b = journalEntryService.deleteEntryById(myId, name);
        if (b) {
            return new ResponseEntity<>(HttpStatus.GONE);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }//Ok

    @PutMapping("id/{id}")
    public HttpEntity<Object> updateJournalById(@PathVariable ObjectId id, @RequestBody JournalEntry newEntry) {
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();
        String name = authentication.getName();
        User user = userService.findByUsername(name);

        List<JournalEntry> collect = user
                .getJournalEntries()
                .stream()
                .filter(a -> a.getId().equals(id))
                .collect(Collectors.toList());
        if (!collect.isEmpty()) {
            Optional<JournalEntry> entryById = journalEntryService.findEntryById(id);
            if (entryById.isPresent()) {
                JournalEntry old = entryById.get();
                old.setDate(LocalDate.now());
                old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
                old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());
                journalEntryService.saveEntry(old);
                return new ResponseEntity<>(old, HttpStatus.OK);
            }
        }


        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }//Ok
}
/*
if we have any issues in the postman then we will gone on
mongoDB Atlas for WHITELISTED-->MongoDB Atlas -> Net-work access
and WHITELIST to the IP ADDRESS WHITELIST.
@Postmapping : Method inside a controller class should
be public so that they can be accessed and
invoke by the spring framework
or external request.

 @RequestBody : it's like saying "hey spring , please take data from the request
 and turn it into a java object that I can use in code."

 @Pathvariable : in this format :-  localhost:8080/journal/id/2
 @RequestParameter : in this format :-  localhost:8080/journal/id?name=pavan

PATHVARIABLE:- IS USED TO DETERMINE PATH OF THE PARTICULAR API'S
REQUESTBODY :- IS USED TO DETERMINE POSTMAN REQUESTS FOR PARTICULAR MAPPINGS

Response Entity :it is the communication in between client and servers.which content following things.
http stats code : 200 it's meant (5) status codes which is to be used
http status code we will be achieved with the help of Responce Entity.
request and responce between client & server.
1xx(information)
2xx(successfull) --> 200(Ok) ,201(Created) ,204(No Content)
3xx(Redirection) --> 301(Moved Permanatly) ,302(Found) ,304(Not modified)
4xx(Client Error) --> 400(Bad request) ,401(Unauthorized) ,403(Forbidden/permission is not allow)
5xx(Server side) --> 500(Internal Server Error) ,502(Bad Gateway) ,503(Service Unavailable)

@ResponceEntity --> is the class which is to be used for

//project sequence : controller -->service -->repository

//            journalEntry.setDate(LocalDate.now()); //becouse of we have to add now date on the given entry.

AWS HOSTING:-https://cloud.mongodb.com/v2/6a5b4d2addebb239ab9d49fb#/explorer/6a5b4d55c51e79adf28ecab8/journaling_entries/user_entries/find
username:pj209380@gmail.com
password:r2u!e@kZ$RYn35L
OWAPS:
 */