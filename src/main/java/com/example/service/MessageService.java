package com.example.service;

import com.example.entity.Message;
import com.example.repository.MessageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

@Service
public class MessageService {
    MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public Message getMessageById(long id) {
        Optional<Message> optionalMessage = messageRepository.findById(id);
        if(optionalMessage.isPresent()) {
            return optionalMessage.get();
        }
        return null;
    }

    public int deleteMessageById(long id) {
        Optional<Message> optionalMessage = messageRepository.findById(id);
        if(optionalMessage.isPresent()) {
            messageRepository.deleteById(id);
            return 1;
        }
        return 0;
    }
}
