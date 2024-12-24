package com.kh.edu.cstad.khotixs.user_profile_service.feature.user_profile.dto;

import java.time.LocalDate;

public record UserProfileResponse(

        String id,
        String fullName,
        String gender,
        LocalDate dob,
        String phoneNumber,
        String address,
        String avatar,
        Number status,
        String position,
        String email,
        String businessName
) {
}
