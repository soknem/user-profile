package com.kh.edu.cstad.khotixs.user_profile_service.config.mongo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public abstract class AuditCollection<T> {

    @CreatedBy
    @Field("created_by")
    protected T createdBy;

    @CreatedDate
    @Field("created_date")
    protected LocalDateTime createdDate;

    @LastModifiedBy
    @Field("last_modified_by")
    protected T lastModifiedBy;

    @LastModifiedDate
    @Field("last_modified_date")
    protected LocalDateTime lastModifiedDate;

}
