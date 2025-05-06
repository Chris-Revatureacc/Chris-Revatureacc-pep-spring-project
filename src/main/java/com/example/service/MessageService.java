package com.example.service;

import com.example.entity.Message;
import com.example.entity.Account;
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

    public Message createNewMessage(Message message) {
        Optional<Account> accountOptional = messageRepository.findAccount(message.getPostedBy());
        if(!message.getMessageText().isEmpty() && message.getMessageText().length() <= 255 && accountOptional.isPresent()) {
            return messageRepository.save(message);
        }
        return null;
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public Message getMessageById(Integer id) {
        Optional<Message> optionalMessage = messageRepository.findById(id);
        if(optionalMessage.isPresent()) {
            return optionalMessage.get();
        }
        return null;
    }

    public int deleteMessageById(Integer id) {
        Optional<Message> optionalMessage = messageRepository.findById(id);
        return messageRepository.deleteMessageById(id);
    }

    public Message updateMessage(Integer id, Message update) {
        Optional<Message> messageOptional = messageRepository.findById(id);
        if(messageOptional.isPresent()) {
            Message message = messageOptional.get();
            message.setMessageText(update.getMessageText());
            messageRepository.save(message);
            return message;
        }
        return null;
    }

    public List<Message> getAllMessagesFromUser(Integer id) {
        List<Message> messageOptional = messageRepository.findMessages(id);
        return messageOptional;
    }
}
