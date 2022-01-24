package com.codebygaurav.texttime.controllers;

import com.codebygaurav.texttime.models.MessageModel;
import com.codebygaurav.texttime.storage.UserStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/text/{to}")
    public void sendMessage(@DestinationVariable String to, MessageModel messageModel){
        System.out.println("handling send message : "+messageModel+" to :"+to);
        boolean isExists = UserStorage.getInstance().getUsers().contains(to);
        if (isExists) {
            simpMessagingTemplate.convertAndSend("/topic/{messages}"+to, messageModel);
        }
    }
}
