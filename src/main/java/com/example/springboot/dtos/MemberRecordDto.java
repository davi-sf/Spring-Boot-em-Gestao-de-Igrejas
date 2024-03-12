package com.example.springboot.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Date;

public record MemberRecordDto(@NotBlank String name, @NotNull String birth, @NotBlank String phone, @NotBlank String address, boolean hasWhatsapp) {


}
