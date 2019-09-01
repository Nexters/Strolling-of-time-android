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
  private String content;
  private String created;
  private long groupID;
  private long id;
  private String title;
  private long userID;
}
