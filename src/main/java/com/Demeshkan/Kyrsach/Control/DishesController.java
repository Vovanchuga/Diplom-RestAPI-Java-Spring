package com.Demeshkan.Kyrsach.Control;

import com.Demeshkan.Kyrsach.Model.Dishes;
import com.Demeshkan.Kyrsach.Repository.DishesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Dishes")
public class DishesController {
    @Autowired
    private DishesRepository dishesRepository;

    @GetMapping("/allDishes")
    public ResponseEntity<List<Dishes>> getDishes(){
        return ResponseEntity.ok(dishesRepository.findAll());
    }
}
