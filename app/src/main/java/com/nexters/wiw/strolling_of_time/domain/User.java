package com.nexters.wiw.strolling_of_time.domain;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class User {
    private Long id;

    private List<MissionHistory> missionHistories;

    private List<GroupNotice> notices;

    private List<GroupMember> members;

    private String nickname;

    private String email;

    private String password;

    private String profileImage;

    private LocalDateTime createdDate;

    @Builder
    public User(String nickname, String email, String password, String profileImage) {
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.profileImage = profileImage;
    }

    public User update(User user) {
        this.nickname = user.nickname;
        this.profileImage = user.profileImage;
        return this;
    }
}