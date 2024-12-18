package com.kh.edu.cstad.khotixs.user_profile_service.feature.user_profile;

import com.kh.edu.cstad.khotixs.user_profile_service.domain.UserProfile;
import com.kh.edu.cstad.khotixs.user_profile_service.feature.user_profile.dto.UserProfileResponse;
import com.kh.edu.cstad.khotixs.user_profile_service.feature.user_profile.dto.UserProfileUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileService{

    private final UserProfileRepository userProfileRepository;
    @Override
    public UserProfileResponse getUserByEmail(String email) {

        UserProfile userProfile = userProfileRepository.findByEmail(email).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("user = %s has not been found", email)));
        UserProfileResponse userProfileResponse = new UserProfileResponse(
                userProfile.getId(),
                userProfile.getFull_name(),
                userProfile.getGender(),
                userProfile.getDob(),
                userProfile.getPhone_number(),
                userProfile.getAddress(),
                userProfile.getAvatar(),
                userProfile.getStatus(),
                userProfile.getPosition(),
                userProfile.getBusiness_name()
        );



        return userProfileResponse;
    }

    @Override
    public Page<UserProfileResponse> getAllUsers(int pageNumber, int pageSize) {
        return null;
    }

    @Override
    public void enableUser(String email) {
        UserProfile userProfile = userProfileRepository.findByEmail(email).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("user = %s has not been found", email)));
        userProfile.setStatus(1);
        userProfileRepository.save(userProfile);
    }

    @Override
    public void disableUser(String email) {
        UserProfile userProfile = userProfileRepository.findByEmail(email).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("user = %s has not been found", email)));
        userProfile.setStatus(0);
        userProfileRepository.save(userProfile);
    }

    @Override
    public UserProfileResponse updateUser(String email, UserProfileUpdateRequest userProfileUpdateRequest) {
        return null;
    }
}
