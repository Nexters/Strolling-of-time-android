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
public class Group {
  private boolean active;
  private String backgroundImage;
  private String category;
  private String created;
  private String description;
  private long id;
  private long memberLimit;
  private String name;
  private String profileImage;
}