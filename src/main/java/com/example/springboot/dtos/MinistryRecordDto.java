package com.example.springboot.dtos;

import jakarta.validation.constraints.NotBlank;

public record MinistryRecordDto (@NotBlank String name){}

