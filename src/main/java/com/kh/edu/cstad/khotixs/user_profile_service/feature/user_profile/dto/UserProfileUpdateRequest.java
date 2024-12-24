package com.kh.edu.cstad.khotixs.user_profile_service.feature.user_profile.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record UserProfileUpdateRequest (

        String fullName,
        String gender,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        LocalDate dob,
        String phoneNumber,
        String address,
        String avatar,
        Number status,
        String position,
        String businessName
) {
}
