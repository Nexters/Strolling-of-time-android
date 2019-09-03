package com.nexters.wiw.strolling_of_time.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GroupPageResponseDto {

    private int size;
    private Long totalElements;
    private int totalPages;
    private int number;
    private List<GroupResponseDto> content;

    @Builder
    public GroupPageResponseDto (int size, Long totalElements, int totalPages, int number, List<GroupResponseDto> content) {
        this.size = size;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.number = number;
        this.content = content;
    }

}