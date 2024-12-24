package com.kh.edu.cstad.khotixs.user_profile_service.feature.user_profile;

import com.kh.edu.cstad.khotixs.user_profile_service.domain.UserProfile;
import com.kh.edu.cstad.khotixs.user_profile_service.feature.user_profile.dto.UserProfileResponse;
import com.kh.edu.cstad.khotixs.user_profile_service.feature.user_profile.dto.UserProfileUpdateRequest;
import com.kh.edu.cstad.khotixs.user_profile_service.kafka.domain.DisableUserProfileEvent;
import com.kh.edu.cstad.khotixs.user_profile_service.kafka.domain.EnableUserProfileEvent;
import com.kh.edu.cstad.khotixs.user_profile_service.kafka.domain.UserProfileUpdateEvent;
import com.kh.edu.cstad.khotixs.user_profile_service.kafka.domain.UserRegisterEvent;
import com.kh.edu.cstad.khotixs.user_profile_service.mapper.UserProfileMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepository userProfileRepository;
    private final UserProfileMapper userProfileMapper;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${kafka.topic.user-profile-update}")
    private String userProfileUpdateTopicName;

    @Value("${kafka.topic.user-register}")
    private String userRegisterTopicName;

    @Value("enable-user-profile-event")
    private String enableUserProfileTopicName;
    @Value("disable-user-profile-event")
    private String disableUserProfileTopicName;

    @Override
    public UserProfileResponse getUserByEmail(String email) {

        // Find user from database
        UserProfile userProfile = userProfileRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("user = %s has not been found", email))
                );

        // Map to DTO response
        return userProfileMapper.toUserProfileResponse(userProfile);
    }

    @Override
    public Page<UserProfileResponse> getAllUsers(int pageNumber, int pageSize) {

        //create sort order
        Sort sortById = Sort.by(Sort.Direction.DESC, "createdDate");

        //create pagination with current pageNumber and pageSize of pageNumber
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortById);

        // Get user from database as page
        Page<UserProfile> userProfiles = userProfileRepository.findAll(pageRequest);

        return userProfiles.map(userProfileMapper::toUserProfileResponse);
    }

    @Override
    public void enableUser(String email) {

        // Find user from database
        UserProfile userProfile = userProfileRepository.findByEmail(email).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("user = %s has not been found", email)));

        // 1 for enable user
        userProfile.setStatus(1);

        // Save to database
        userProfileRepository.save(userProfile);

        EnableUserProfileEvent enableUserProfileEvent = EnableUserProfileEvent.newBuilder()
                .setStatus((int) userProfile.getStatus())
                .setEmail(userProfile.getEmail())
                .build();
        log.info("EnableUserProfileEvent: {}", enableUserProfileEvent);
        kafkaTemplate.send(enableUserProfileTopicName, userProfile.getId(), enableUserProfileEvent);

    }

    @Override
    public void disableUser(String email) {

        // Find user from database
        UserProfile userProfile = userProfileRepository.findByEmail(email).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("user = %s has not been found", email)));

        // 0 for disable user
        userProfile.setStatus(0);

        // Save to database
        userProfileRepository.save(userProfile);

        DisableUserProfileEvent disableUserProfileEvent = DisableUserProfileEvent.newBuilder()
                .setStatus((Integer) userProfile.getStatus())
                .setEmail(userProfile.getEmail())
                .build();
        kafkaTemplate.send(disableUserProfileTopicName, userProfile.getId(), disableUserProfileEvent);
    }

    @Override
    public UserProfileResponse updateUser(String email, UserProfileUpdateRequest userProfileUpdateRequest) {

        // Find user from database
        UserProfile userProfile = userProfileRepository.findByEmail(email).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("user = %s has not been found", email)));

        userProfileMapper.updateUserProfileFromRequest(userProfile, userProfileUpdateRequest);

        // Save to database
        userProfileRepository.save(userProfile);

        UserProfileUpdateEvent userProfileUpdateEvent = UserProfileUpdateEvent.newBuilder()
                .setFullName(userProfile.getFullName())
                .setGender(userProfile.getGender())
                .setDob(String.valueOf(userProfile.getDob()))
                .setPhoneNumber(userProfile.getPhoneNumber())
                .setAddress(userProfile.getAddress())
                .setAvatar(userProfile.getAvatar())
                .setStatus((Integer) userProfile.getStatus())
                .setPosition(userProfile.getPosition())
                .setBusinessName(userProfile.getBusinessName())
                .build();

        kafkaTemplate.send(userProfileUpdateTopicName, userProfile.getId(), userProfileUpdateEvent);

        return userProfileMapper.toUserProfileResponse(userProfile);
    }
}
