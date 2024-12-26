package com.kh.edu.cstad.khotixs.user_profile_service.feature.user_profile;

import com.kh.edu.cstad.khotixs.user_profile_service.feature.user_profile.dto.UserProfileResponse;
import com.kh.edu.cstad.khotixs.user_profile_service.feature.user_profile.dto.UserProfileUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user-profiles")
@Slf4j
public class UserProfileController {
    private final UserProfileService userProfileService;

    @GetMapping("/me")
    public UserProfileResponse getMe(Authentication authentication){

        log.info("Authentication:{}",authentication);
        return userProfileService.findMe(authentication);
    }


    @GetMapping("/{email}")
    public UserProfileResponse getUserByEmail(@PathVariable String email){
       return userProfileService.getUserByEmail(email);
    }

    @GetMapping
    public Page <UserProfileResponse> getAllUsers(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "25") int pageSize){
        return userProfileService.getAllUsers(pageNumber, pageSize);
    }

    @PutMapping("/{email}/disable")
    @ResponseStatus(HttpStatus.CREATED)
    public void disableUsers(@PathVariable String email){
        userProfileService.disableUser(email);
    }

    @PutMapping("/{email}/enable")
    public void enableUsers(@PathVariable String email){
        userProfileService.enableUser(email);
    }

    @PatchMapping("/{email}")
    private UserProfileResponse updateByEmail(@PathVariable String email,@Valid @RequestBody UserProfileUpdateRequest userProfileUpdateRequest){
        return  userProfileService.updateUser(email, userProfileUpdateRequest);
    }
}
