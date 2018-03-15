package com.demo.fashion.media;

import com.demo.fashion.media.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.ArrayList;

@SpringBootApplication
public class Application {


    @Autowired
    private ClothingRepository clothingRepository;

    @Autowired
    private OutfitRepository clothingCollectionRepository;


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
        System.out.println("hello world, I have just started up");
        startup();
    }


    private void startup() {
        for (int i = 0; i < 10; i++) {
            Clothing clothing = new Clothing();
            clothing.setImgUrl("http://www.img.com/" + (1 + i));
            clothing.setName("dress " + (i + 1));
            clothingRepository.save(clothing);
        }

        Outfit collection = new Outfit();
        collection.setClothingList(new ArrayList<>());
        Owner owner = new Owner();
        collection.setOwner(owner);
        for (Clothing clothing : clothingRepository.findAll()) {
            OutfitEntry entry = new OutfitEntry();
            entry.setClothing(clothing);
            entry.setClothingCollection(collection);
            entry.setPosition("12,20, 112, 70"); // a square coordinates x1, y1, x2, y2
            collection.getClothingList().add(entry);
        }

        clothingCollectionRepository.save(collection);
    }
}