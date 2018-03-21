package com.demo.fashion.media;


import com.demo.fashion.media.data.Outfit;
import com.demo.fashion.media.data.OutfitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class OutfitController {

    @Autowired
    private OutfitRepository outfitRepository;



    @RequestMapping(value = "outfit/{id}", method = RequestMethod.GET)
    public Outfit getOutfit(@PathVariable(name = "id") Long id) {
        return outfitRepository.findById(id).get();
    }

    @RequestMapping(value = "outfit", method = RequestMethod.GET)
    public List<Outfit> getOutfit() {
        List<Outfit> outfits = new ArrayList<>();
        outfitRepository.findAll().forEach(c -> outfits.add(c));
        return outfits;
    }

    @RequestMapping(value = "outfit", method = RequestMethod.POST)
    public Outfit saveOutfit(Outfit outfit) {
        return outfitRepository.save(outfit);
    }

    @RequestMapping(value = "outfit/{id}", method = RequestMethod.DELETE)
    public String removeOutfit(@PathVariable(name="id") Long id) {
        Outfit outfit = outfitRepository.findById(id).get();
        outfitRepository.delete(outfit);
        return "SUCCESS";//here all needs serious revamp
    }
    
    

}
