package com.demo.fashion.look;


import com.demo.fashion.OperationResult;
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
    public OperationResult<List<Outfit>> getOutfit() {
        List<Outfit> outfits = new ArrayList<>();
        outfitRepository.findAll().forEach(c -> outfits.add(c));
        return OperationResult.succes(outfits);
    }

    @RequestMapping(value = "outfit", method = RequestMethod.POST)
    public  OperationResult<Outfit> saveOutfit(Outfit outfit) {
        return OperationResult.succes(outfitRepository.save(outfit));
    }

    @RequestMapping(value = "outfit/{id}", method = RequestMethod.DELETE)
    public OperationResult<String> removeOutfit(@PathVariable(name="id") Long id) {
        Outfit outfit = outfitRepository.findById(id).get();
        outfitRepository.delete(outfit);
        return OperationResult.succes("SUCCESS");//here all needs serious revamp
    }
    
    

}
