package com.example.toys_inventory.Service;

import com.example.toys_inventory.DataModel.Toy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToyRepository extends JpaRepository<Toy, Integer> {
}
