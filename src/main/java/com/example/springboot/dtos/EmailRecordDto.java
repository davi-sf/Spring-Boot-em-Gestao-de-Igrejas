package com.example.springboot.dtos;

import jakarta.validation.constraints.NotBlank;

public record EmailRecordDto (@NotBlank String to, String subject, String body){

}

