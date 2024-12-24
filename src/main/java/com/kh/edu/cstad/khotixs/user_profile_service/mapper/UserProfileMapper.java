package com.kh.edu.cstad.khotixs.user_profile_service.mapper;

import com.kh.edu.cstad.khotixs.user_profile_service.domain.UserProfile;
import com.kh.edu.cstad.khotixs.user_profile_service.feature.user_profile.dto.UserProfileResponse;
import com.kh.edu.cstad.khotixs.user_profile_service.feature.user_profile.dto.UserProfileUpdateRequest;
import com.kh.edu.cstad.khotixs.user_profile_service.kafka.domain.DisableUserProfileEvent;
import com.kh.edu.cstad.khotixs.user_profile_service.kafka.domain.EnableUserProfileEvent;
import com.kh.edu.cstad.khotixs.user_profile_service.kafka.domain.UserRegisterEvent;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {
    UserProfileResponse toUserProfileResponse(UserProfile userProfile);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserProfileFromRequest(@MappingTarget UserProfile userProfile, UserProfileUpdateRequest userProfileUpdateRequest);

    UserProfile fromUserRegisterEvent(UserRegisterEvent userRegisterEvent);

    UserProfile fromEnableUserProfileEvent(EnableUserProfileEvent enableUserProfileEvent);
    UserProfile fromDisableUserProfileEvent(DisableUserProfileEvent disableUserProfileEvent);

}
