package com.nexters.wiw.strolling_of_time.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {
    private List<User> users;
    private PageMetadata pageMetadata;

    @Getter
    @Setter
    public class PageMetadata{
        private Long size;
        private Long totalElements;
        private Long totalPages;
        private Long number;
    }

    @Getter
    @Setter
    public class User{
        private Long id;
        private String nickname;
        private String email;
        private String profileImage;
        private String createdTime;
    }

    @Builder

    public UserResponseDto(List<User> users, PageMetadata pageMetadata) {
        this.users = users;
        this.pageMetadata = pageMetadata;
    }
}