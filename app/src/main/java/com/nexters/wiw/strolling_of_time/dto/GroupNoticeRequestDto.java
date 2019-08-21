package com.nexters.wiw.strolling_of_time.dto;

import java.time.LocalDateTime;

public class GroupNoticeRequestDto {
  private String content;
  private LocalDateTime created;
  private String title;

  public GroupNoticeRequestDto(
          String content, LocalDateTime created, String title
  ) {
    this.content = content;
    this.created = created;
    this.title = title;
  }
}
