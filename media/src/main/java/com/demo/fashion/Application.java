package com.demo.fashion;

import com.demo.fashion.look.*;
import com.demo.fashion.users.Owner;
import com.demo.fashion.users.auth.Role;
import com.demo.fashion.users.auth.RoleName;
import com.demo.fashion.users.auth.RoleRepository;
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

    @Autowired
    private RoleRepository roleRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
        System.out.println("hello world, I have just started up");
        startup();
    }


    private void startup() {
        roleRepository.save(new Role(RoleName.ROLE_ADMIN));
        roleRepository.save(new Role(RoleName.ROLE_USER));

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