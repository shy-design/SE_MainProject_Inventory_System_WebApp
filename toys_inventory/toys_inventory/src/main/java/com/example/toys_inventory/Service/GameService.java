package com.example.toys_inventory.Service;

import com.example.toys_inventory.DataModel.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

   // public List<Game> listGames(){ return gameRepository.findAll(); }
}
