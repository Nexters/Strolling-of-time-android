package com.nexters.wiw.strolling_of_time.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
public class UserModifyRequestDto {
    private String nickname;

    private String password;

    private String profileImage;
}
