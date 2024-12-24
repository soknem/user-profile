package com.kh.edu.cstad.khotixs.user_profile_service.feature.user_profile.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record UserProfileRequest(


        @NotBlank (message = "fullName is require")
        String fullName,
        @NotBlank (message = "fullName is require")
        String gender,
        LocalDate dob,
        String phoneNumber,
        String address,
        String avatar,
        Number status,
        String position,
        String businessName
) {

}
