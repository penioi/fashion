package com.demo.fashion.media.data;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class OutfitEntry {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id;
    @NotNull
    @ManyToOne
    private Outfit clothingCollection;
    @ManyToOne
    private Clothing clothing;
    private String position;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonIgnore
    public Outfit getClothingCollection() {
        return clothingCollection;
    }

    public void setClothingCollection(Outfit clothingCollection) {
        this.clothingCollection = clothingCollection;
    }

    public Clothing getClothing() {
        return clothing;
    }

    public void setClothing(Clothing clothing) {
        this.clothing = clothing;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
