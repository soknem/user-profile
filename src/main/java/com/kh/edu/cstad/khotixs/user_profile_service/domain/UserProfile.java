package com.kh.edu.cstad.khotixs.user_profile_service.domain;

import com.kh.edu.cstad.khotixs.user_profile_service.config.mongo.AuditCollection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "user_profiles")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserProfile extends AuditCollection<String> {

    @Id
    private String id;
    private String full_name;
    private String gender;
    private LocalDate dob;
    private String phone_number;
    private String address;
    private String avatar;
    private Number status;
    private String position;
    private String email;
    private String business_name;

}
