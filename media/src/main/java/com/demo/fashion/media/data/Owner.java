package com.demo.fashion.media.data;

import javax.persistence.*;
import java.util.List;

@Entity
public class Owner {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id;

    @OneToMany
    private List<Outfit> collections;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
