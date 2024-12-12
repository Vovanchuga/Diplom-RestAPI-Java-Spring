package com.Demeshkan.Kyrsach.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Dishes")
public class Dishes {
    @Id
    @GeneratedValue(generator = "DishesGenerator")
    @SequenceGenerator(
            name = "DishesIDGenerator",
            sequenceName = "DishesSequense",
            initialValue = 1
    )
    private Integer dishID;

    @NotBlank
    private String Name;

    public Integer getDishID() {
        return dishID;
    }

    public void setDishID(Integer dishID) {
        this.dishID = dishID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

}
