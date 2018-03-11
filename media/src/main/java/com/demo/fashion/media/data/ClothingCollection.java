package com.demo.fashion.media.data;

import javax.persistence.*;
import java.util.List;

@Entity
public class ClothingCollection {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany
    private List<Clothing> clothingList;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Clothing> getClothingList() {
        return clothingList;
    }

    public void setClothingList(List<Clothing> clothingList) {
        this.clothingList = clothingList;
    }
}
