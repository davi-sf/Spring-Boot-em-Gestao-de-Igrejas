package com.example.springboot.controllers;

import com.example.springboot.dtos.EmailRecordDto;
import com.example.springboot.models.EmailModel;
import com.example.springboot.services.EmailService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("email")
public class EmailController {
    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping()
    public void sendEmail(@RequestBody EmailRecordDto email) {
        emailService.sendEmail(email);
    }
}
