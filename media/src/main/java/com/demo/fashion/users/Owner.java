package com.demo.fashion.users;

import com.demo.fashion.look.Outfit;

import javax.persistence.*;
import java.util.List;

@Entity
public class Owner {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id;

    @OneToMany
    private List<Outfit> collections;

    @ManyToOne
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
