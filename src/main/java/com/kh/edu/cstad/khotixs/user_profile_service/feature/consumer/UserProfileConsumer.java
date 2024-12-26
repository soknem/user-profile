package com.kh.edu.cstad.khotixs.user_profile_service.feature.consumer;

import com.kh.edu.cstad.khotixs.core.domain.UserRegisterEvent;
import com.kh.edu.cstad.khotixs.user_profile_service.domain.UserProfile;
import com.kh.edu.cstad.khotixs.user_profile_service.feature.user_profile.UserProfileRepository;
import com.kh.edu.cstad.khotixs.user_profile_service.mapper.UserProfileMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

@RequiredArgsConstructor
@Component
@Slf4j
@KafkaListener(
        topics = {"${kafka.topic.user-register}"},
        groupId = "${kafka.consumer.group-id}",
        containerFactory = "kafkaListenerContainerFactory"
)
public class UserProfileConsumer {

    private final UserProfileMapper userProfileMapper;
    private final UserProfileRepository userProfileRepository;


    @KafkaHandler
    public void handleUserRegisterEvent(UserRegisterEvent userRegisterEvent) {

        log.info("UserRegisterEvent: {}", userRegisterEvent);

        UserProfile userProfile = new UserProfile();

        userProfile.setFullName(userRegisterEvent.getFullName());
        userProfile.setGender(userRegisterEvent.getGender());
        userProfile.setDob(LocalDate.parse(userRegisterEvent.getDob()));
        userProfile.setPhoneNumber(userRegisterEvent.getPhoneNumber());
        userProfile.setAddress(userRegisterEvent.getAddress());
        userProfile.setAvatar(userRegisterEvent.getAvatar());
        userProfile.setStatus(userRegisterEvent.getStatus());
        userProfile.setPosition(userRegisterEvent.getPosition());
        userProfile.setEmail(userRegisterEvent.getEmail());
        userProfile.setBusinessName(userRegisterEvent.getBusinessName());

        userProfileRepository.save(userProfile);

        log.info("LogUserProfile: {}", userProfile);
    }
}
