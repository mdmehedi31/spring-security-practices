package com.sps.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/msg")
public class MessageController {

    private static final Logger log = LoggerFactory.getLogger(MessageController.class);
    @GetMapping("/get")
    public ResponseEntity<String> getMessage(){
        String message = "This message sent from my site, can you give me the access of the all code";
        return ResponseEntity.ok(message);
    }

    @PostMapping("/sent")
    public ResponseEntity<String> sendMessage(@RequestParam String message){
        log.info("User message : "+message);
        return ResponseEntity.ok(message);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateMessage(@RequestParam String message){
        log.info("Update user message : "+message);
        return ResponseEntity.ok(message);
    }

    @DeleteMapping("/delete-msg")
    public ResponseEntity<String> deleteMessage(@PathVariable Integer msgid){
        log.info("Delete user message : "+msgid);
        return ResponseEntity.ok("Deleted user message  id is : "+msgid);
    }
}
