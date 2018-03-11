package com.demo.fashion.media;

import com.demo.fashion.media.data.Clothing;
import com.demo.fashion.media.data.ClothingCollection;
import com.demo.fashion.media.data.ClothingCollectionRepository;
import com.demo.fashion.media.data.ClothingRepository;
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
    private ClothingCollectionRepository clothingCollectionRepository;


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
        ClothingCollection collection = new ClothingCollection();
        collection.setClothingList(new ArrayList<>());
        for (Clothing clothing : clothingRepository.findAll()) {
            collection.getClothingList().add(clothing);
        }

        clothingCollectionRepository.save(collection);
    }
}