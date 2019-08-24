package com.nexters.wiw.strolling_of_time.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupResponseDto {
    private List<Content> content;
    private Long number;
    private Long size;
    private Long totalElements;
    private Long totalPages;

    @Getter
    @Setter
    public class Content{
        private Boolean active;
        private String backgroundImage;
        private String category;
        private String created;
        private String description;
        private Long id;
        private Long memberLimit;
        private String name;
        private String profileImage;
    }
}
