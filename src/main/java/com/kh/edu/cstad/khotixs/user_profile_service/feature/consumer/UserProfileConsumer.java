package com.kh.edu.cstad.khotixs.user_profile_service.feature.consumer;

import com.kh.edu.cstad.khotixs.user_profile_service.domain.UserProfile;
import com.kh.edu.cstad.khotixs.user_profile_service.feature.user_profile.UserProfileRepository;
import com.kh.edu.cstad.khotixs.user_profile_service.kafka.domain.UserProfileUpdateEvent;
import com.kh.edu.cstad.khotixs.user_profile_service.kafka.domain.UserRegisterEvent;
import com.kh.edu.cstad.khotixs.user_profile_service.mapper.UserProfileMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
@KafkaListener(
        topics = {"${kafka.topic.user-register}", "${kafka.topic.user-profile-update}"},
        groupId = "${kafka.consumer.group-id}",
        containerFactory = "kafkaListenerContainerFactory"
)
public class UserProfileConsumer {

    private final UserProfileMapper userProfileMapper;
    private final UserProfileRepository userProfileRepository;

    @KafkaHandler
    public void handleUserProfileUpdateEvent(UserProfileUpdateEvent userProfileUpdateEvent) {
        log.info("UserProfileUpdateEvent:{}",userProfileUpdateEvent);
    }

    @KafkaHandler
    public void handleUserRegisterEvent(UserRegisterEvent userRegisterEvent) {
        log.info("UserRegisterEvent: {}", userRegisterEvent);
        // Process the event
        UserProfile userProfile = userProfileMapper.fromUserRegisterEvent(userRegisterEvent);
        userProfileRepository.save(userProfile);
        log.info("LogUserProfile: {}", userProfile);
    }
}
