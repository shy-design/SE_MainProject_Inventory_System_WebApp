package com.example.toys_inventory.Service;

import com.example.toys_inventory.DataModel.Toy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToyService {

    @Autowired
    private ToyRepository toyRepo;

   public List<Toy> listAll(){ return (List<Toy>) toyRepo.findAll(); }

    public void save(Toy toy){ toyRepo.save(toy); }

    public Toy get(Integer id){ return toyRepo.findById(id).get(); }

    public void delete(Integer id){ toyRepo.deleteById(id); }
}
