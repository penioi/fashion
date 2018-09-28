package com.demo.fashion.look;

import com.demo.fashion.OperationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController()
public class ClothingController {

    @Autowired
    private ClothingRepository clothingRepository;

    @RequestMapping(value = "clothing/{id}", method = RequestMethod.GET)
    public OperationResult<Clothing> getCloting(@PathVariable(name = "id") Long id) {
        return OperationResult.succes(clothingRepository.findById(id).get());
    }

    @RequestMapping(value = "clothing", method = RequestMethod.GET)
    public OperationResult<List<Clothing>> getCloting() {
        List<Clothing> clothings = new ArrayList<>();
        clothingRepository.findAll().forEach(c -> clothings.add(c));
        return OperationResult.succes(clothings);
    }

    @RequestMapping(value = "clothing", method = RequestMethod.POST)
    public OperationResult<Clothing> saveCloting(Clothing clothing) {
        return OperationResult.succes(clothingRepository.save(clothing));
    }

    @RequestMapping(value = "clothing/{id}", method = RequestMethod.DELETE)
    public String removeCloting(@PathVariable(name="id") Long id) {
        Clothing clothing = clothingRepository.findById(id).get();
        clothingRepository.delete(clothing);
        return  "SUCCESS";//here all needs serious revamp
    }
    
}
