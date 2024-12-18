package com.kh.edu.cstad.khotixs.user_profile_service.feature.user_profile.dto;

import java.time.LocalDate;

public record UserProfileResponse(

        String id,
        String full_name,
        String gender,
        LocalDate dob,
        String phone_number,
        String address,
        String avatar,
        Number status,
        String position,
        String business_name
) {
}
