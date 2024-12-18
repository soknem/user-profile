package com.kh.edu.cstad.khotixs.user_profile_service.feature.user_profile;

import com.kh.edu.cstad.khotixs.user_profile_service.feature.user_profile.dto.UserProfileResponse;
import com.kh.edu.cstad.khotixs.user_profile_service.feature.user_profile.dto.UserProfileUpdateRequest;
import org.springframework.data.domain.Page;

public interface UserProfileService {
    UserProfileResponse getUserByEmail(String email);
    Page<UserProfileResponse> getAllUsers(
            int pageNumber,
            int pageSize
    );

    void enableUser(String email);
    void disableUser(String email);

    UserProfileResponse updateUser(String email, UserProfileUpdateRequest userProfileUpdateRequest);

}
