package com.Demeshkan.Kyrsach.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
@Entity
@Table(name = "MenuAndDishes")
public class MenuAndDishes {
    @Id
    @GeneratedValue(generator = "MenuAndDishesGenerator")
    @SequenceGenerator(
            name = "MenuAndDishesIDGenerator",
            sequenceName = "MenuAndDishesSequense",
            initialValue = 1
    )
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "dishID", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Dishes dishes;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "menuID", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Menu menu;

    @NotNull
    private Integer Calories;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Dishes getDishes() {
        return dishes;
    }

    public void setDishes(Dishes dishes) {
        this.dishes = dishes;
    }

    public Integer getCalories() {
        return Calories;
    }

    public void setCalories(Integer calories) {
        Calories = calories;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
}
