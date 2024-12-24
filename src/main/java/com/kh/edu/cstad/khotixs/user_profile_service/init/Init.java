package com.kh.edu.cstad.khotixs.user_profile_service.init;

import com.kh.edu.cstad.khotixs.user_profile_service.domain.UserProfile;
import com.kh.edu.cstad.khotixs.user_profile_service.feature.user_profile.UserProfileRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
@Slf4j
public class Init {

    private final UserProfileRepository userProfileRepository;
    String[][] userData = {
            {"Chan Dara", "Male", "1990-05-15", "0123456789", "Phnom Penh", "chan.dara@example.com", "Dara Group"},
            {"Srey Leak", "Female", "1992-08-22", "0987654321", "Siem Reap", "srey.leak@example.com", "Leak Enterprise"},
            {"Rith Sok", "Male", "1985-12-10", "0109876543", "Battambang", "rith.sok@example.com", "Sok Ventures"},
            {"Bopha Meas", "Female", "1998-03-25", "0151234567", "Kampot", "bopha.meas@example.com", "Meas Holdings"},
            {"Visal Chhim", "Male", "1991-11-30", "0112233445", "Takeo", "visal.chhim@example.com", "Chhim Corp"},
            {"Pich Seyha", "Female", "1995-06-05", "0163344556", "Kandal", "pich.seyha@example.com", "Seyha Ltd."},
            {"Sokha Kim", "Male", "1987-02-18", "0126677889", "Prey Veng", "sokha.kim@example.com", "Kim Logistics"},
            {"Chanda Koy", "Female", "1993-07-13", "0147788990", "Koh Kong", "chanda.koy@example.com", "Koy Adventures"},
            {"Vireak Thy", "Male", "1989-09-09", "0178899001", "Svay Rieng", "vireak.thy@example.com", "Thy Solutions"},
            {"Sothea Phan", "Female", "1996-04-20", "0189900112", "Sihanoukville", "sothea.phan@example.com", "Phan Group"}
    };

    @PostConstruct
    void initUser(){
        if (userProfileRepository.count() < 1){
            for (String[] user : userData) {
                UserProfile userProfile = new UserProfile();
                userProfile.setFullName(user[0]);
                userProfile.setGender(user[1]);
                userProfile.setDob(LocalDate.parse(user[2]));
                userProfile.setPhoneNumber(user[3]);
                userProfile.setAddress(user[4]);
                userProfile.setAvatar(user[0].toLowerCase().replace(" ", "_") + ".png");
                userProfile.setStatus(1);
                userProfile.setPosition("Staff");
                userProfile.setEmail(user[5]);
                userProfile.setBusinessName(user[6]);

                userProfileRepository.save(userProfile);
                log.info("userProfile:{}", userProfile);
            }
        }
    }

}
