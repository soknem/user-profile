package com.kh.edu.cstad.khotixs.user_profile_service.base;


import lombok.*;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class BasedResponse<T> {
    private T payload;
}
