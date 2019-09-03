package com.nexters.wiw.strolling_of_time.dto;

import lombok.Getter;

@Getter
public class GroupResponseDto {
    private long id;
    private boolean active;
    private String backgroundImage;
    private String category;
    private String description;
    private long memberLimit;
    private String created;
    private String name;
    private String profileImage;
}
