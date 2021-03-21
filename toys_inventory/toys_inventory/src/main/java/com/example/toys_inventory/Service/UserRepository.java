package com.example.toys_inventory.Service;

import com.example.toys_inventory.DataModel.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
}
