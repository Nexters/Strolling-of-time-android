package com.nexters.wiw.strolling_of_time.domain;


import com.nexters.wiw.strolling_of_time.dto.Group;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class GroupMember {
  private Group group;

  private User user;

  private boolean permission;

  @Builder
  public GroupMember(Group group, User user, Boolean permission) {
    this.group = group;
    this.user = user;
    this.permission = permission;
  }
}
