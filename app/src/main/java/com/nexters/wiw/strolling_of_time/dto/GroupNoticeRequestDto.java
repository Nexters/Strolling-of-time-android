package com.nexters.wiw.strolling_of_time.dto;

public class GroupNoticeRequestDto {
  private String content;
  private String created;
  private String title;

  public GroupNoticeRequestDto(
          String content, String created, String title
  ) {
    this.content = content;
    this.created = created;
    this.title = title;
  }
}
