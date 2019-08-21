package com.nexters.wiw.strolling_of_time.domain;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
class Group {
  private Long id;

  private String name;

  private String description;

  private String profileImage;

  private String backgroundImage;

  private LocalDateTime created;

  private int memberLimit;

  private boolean active;

}