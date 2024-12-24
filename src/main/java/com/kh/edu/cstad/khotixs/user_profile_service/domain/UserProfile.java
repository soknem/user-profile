package com.kh.edu.cstad.khotixs.user_profile_service.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kh.edu.cstad.khotixs.user_profile_service.config.mongo.AuditCollection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
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
    private String fullName;
    private String gender;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dob;
    private String phoneNumber;
    private String address;
    private String avatar;
    private Number status;
    private String position;

    @Indexed(unique = true)
    private String email;
    private String businessName;

}
