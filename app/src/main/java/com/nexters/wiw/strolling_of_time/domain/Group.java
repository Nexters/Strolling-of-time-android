package com.nexters.wiw.strolling_of_time.domain;

import java.time.LocalDateTime;

public class Group {
  private Long id;

  private String name;

  private String description;

  private String profileImage;

  private String backgroundImage;

  private LocalDateTime created;

  private int memberLimit;

  private boolean active;
}