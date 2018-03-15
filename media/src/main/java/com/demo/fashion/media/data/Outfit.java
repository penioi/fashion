package com.demo.fashion.media.data;

import javax.persistence.*;
import java.util.List;

@Entity
public class Outfit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String outfitName;

    @OneToMany(cascade =  CascadeType.ALL)
    private List<OutfitEntry> clothingList;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Owner owner;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<OutfitEntry> getClothingList() {
        return clothingList;
    }

    public void setClothingList(List<OutfitEntry> clothingList) {
        this.clothingList = clothingList;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String getOutfitName() {
        return outfitName;
    }

    public void setOutfitName(String outfitName) {
        this.outfitName = outfitName;
    }

}
