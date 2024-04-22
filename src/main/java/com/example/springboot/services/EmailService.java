package com.example.springboot.services;


import com.example.springboot.dtos.EmailRecordDto;
import com.example.springboot.models.EmailModel;
import com.example.springboot.models.MemberModel;
import jakarta.validation.constraints.Email;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailService {
    private final JavaMailSender emailSender;
    private MemberService memberService;

    public EmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendEmailToAllMembers(String subject, String body) {
        List<MemberModel> members = memberService.getAllMembers();

        for (MemberModel member : members) {
            EmailRecordDto emailDto = new EmailRecordDto(member.getEmail(), subject, body);
            sendEmail(emailDto);
        }
    }

    public void sendEmail(EmailRecordDto email){
        var msg = new SimpleMailMessage();
        msg.setFrom("noreply@email.com");
        msg.setTo(email.to());
        msg.setSubject(email.subject());
        msg.setText(email.body());
        emailSender.send(msg);

    }


}
