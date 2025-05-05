package com.example.controller;

import com.example.entity.*;
import com.example.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {
    AccountService accountService;
    MessageService messageService;

    @Autowired
    public SocialMediaController(AccountService accountService, MessageService messageService) { 
        this.accountService = accountService;
        this.messageService = messageService;
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody Account account) {
        Account newAcc = accountService.addAccount(account);
        if(account.getUsername().isBlank() || account.getPassword().length() < 4) {
            return ResponseEntity.status(400).body("Error: Invalid username input or invalid password length");
        }
        if(newAcc != null) {
            return ResponseEntity.ok(newAcc);
        } else {
            return ResponseEntity.status(409).body("Error: Username already in use, please try a different username");
        }
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody Account account) {
        Account log = accountService.loginAccount(account.getUsername(), account.getPassword());
        if(log != null) {
            return ResponseEntity.ok(log);
        } else {
            return ResponseEntity.status(401).body("Error: Unauthorized.");
        }
    }

    @PostMapping("/messages")
    public ResponseEntity createNewMessage(@RequestBody Message message) {
        Message msg = messageService.createNewMessage(message);
        if(msg != null) {
            return ResponseEntity.ok(msg);
        } else {
            return ResponseEntity.status(400).body("Error: message not created.");
        }
    }

    @GetMapping("/messages")
    public ResponseEntity getAllMessages() {
        return ResponseEntity.ok(messageService.getAllMessages());
    }

    @GetMapping("/messages/{message_id}")
    public ResponseEntity getMessageById(@PathVariable long message_id) {
        Message msg = messageService.getMessageById(message_id);
        if(msg != null) {
            return ResponseEntity.ok(msg);
        } else {
            return ResponseEntity.ok().build();
        }
    }

    @DeleteMapping("/messages/{message_id}")
    public ResponseEntity deleteMessageById(@PathVariable long message_id) {
        Message msg = messageService.getMessageById(message_id);
        int flag = messageService.deleteMessageById(message_id);
        if(flag == 1) {
            return ResponseEntity.ok(msg); 
        } else {
            return ResponseEntity.ok().build();
        }
    }

    @PatchMapping("/messages/{message_id}")
    public ResponseEntity updateMessageById(@PathVariable long message_id, @RequestBody Message message) {
        Message msg = messageService.updateMessage(message_id, message);
        if(msg != null) {
            return ResponseEntity.ok(1);
        } else {
            return ResponseEntity.status(400).body("Client Error");
        }
    }

    @GetMapping("/accounts/{account_id}/messages")
    public ResponseEntity getAllMessagesFromUser(@PathVariable Integer account_id) {
        return ResponseEntity.ok(messageService.getAllMessagesFromUser(account_id));
        
    }
}
