package com.nexters.wiw.strolling_of_time.domain;


import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupNotice {
  private long id;

  //groupNotice : group (N:1)
  private Group group;

  //groupNotice : user (N:1)
  private User user;

  private String title;

  private String content;

  private LocalDateTime created;

  public GroupNotice update(GroupNotice groupNotice) {
    this.title = groupNotice.getTitle();
    this.content = groupNotice.getContent();
    this.created = groupNotice.getCreated();

    return this;
  }
}
