package com.krunal.api.email.controller;

import com.krunal.api.email.dto.JiraRequest;
import com.krunal.api.email.dto.MailRequest;
import com.krunal.api.email.dto.MailResponse;
import com.krunal.api.email.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "v1")
public class NotificationController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/sendEmail")
    public MailResponse sendMail(@RequestBody MailRequest request){
        Map<String,Object> model = new HashMap<>();
        model.put("Name",request.getName());
        model.put("product",request.getProduct());
        model.put("link",request.getLink());

        return emailService.sendEmail(request,model);
    }

    @PostMapping("/sendSymphony")
    public String sendSymphonyMessage(@RequestParam(value = "message") String message){
        return message;
    }


}
