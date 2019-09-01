package com.nexters.wiw.strolling_of_time.dto;


import java.util.Collection;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSearchResponseDto {
    private Collection<SignUpResponseDto> users;

    private PageMetadata PageMetadata;

    public UserSearchResponseDto(Collection<SignUpResponseDto> users, PageMetadata pageMetadata) {
        this.users = users;
        this.PageMetadata = pageMetadata;
    }
}
