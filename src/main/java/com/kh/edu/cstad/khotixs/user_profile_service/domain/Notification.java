package com.kh.edu.cstad.khotixs.user_profile_service.domain;

import com.kh.edu.cstad.khotixs.user_profile_service.config.mongo.AuditCollection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Document(collection = "assets")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Notification  extends AuditCollection<String> {

    @Id
    private String id;

    @Field("title")
    private String title;

    @Field("description")
    private String description;

    private LocalDateTime date;


}
