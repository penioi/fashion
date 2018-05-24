package com.demo.fashion.users;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class LoginSession {

    @Id
    private String token;
    @ManyToOne
    private User user;

    private Date created;

    private Date lstAccessed;

    private boolean active;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getLstAccessed() {
        return lstAccessed;
    }

    public void setLstAccessed(Date lstAccessed) {
        this.lstAccessed = lstAccessed;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
