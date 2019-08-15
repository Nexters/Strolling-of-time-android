package com.nexters.wiw.strolling_of_time.models;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.OrderBy;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.Date;
import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

@Entity(nameInDb="users")
public class Users{				// 객체 하나가 하나의 Row
    @Id(autoincrement = true)  	// AUTO_INCREASE
    private Long id; 				// id는 Long으로

    @Property(nameInDb="name")		// Column 이름
    private String name;

    @Property(nameInDb="email")		// Column 이름
    private String email;

    @Property(nameInDb="password")		// Column 이름
    private String password;

    @Property(nameInDb="profile_image")		// Column 이름
    private String profile_image;

    @Property(nameInDb="background_image")		// Column 이름
    private String background_image;

    @Property(nameInDb="created")		// Column 이름
    private Date created;

    @Property(nameInDb="active")		// Column 이름
    private boolean active;

    @Generated(hash = 1576133933)
    public Users(Long id, String name, String email, String password,
            String profile_image, String background_image, Date created,
            boolean active) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.profile_image = profile_image;
        this.background_image = background_image;
        this.created = created;
        this.active = active;
    }

    @Generated(hash = 2146996206)
    public Users() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfile_image() {
        return this.profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public String getBackground_image() {
        return this.background_image;
    }

    public void setBackground_image(String background_image) {
        this.background_image = background_image;
    }

    public Date getCreated() {
        return this.created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public boolean getActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

//    @ToMany(referencedJoinProperty = "postId")	// 일대다 관계 Join
//    @OrderBy("created ASC")						// 오름차순 정렬
//    private List<Reply> replies;					// 댓글들
}
