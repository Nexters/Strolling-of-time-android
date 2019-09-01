package com.nexters.wiw.strolling_of_time.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignUpResponseDto {

    private long id;

    private String nickname;

    private String email;

    private String profileImage;

    private String createdTime;

}