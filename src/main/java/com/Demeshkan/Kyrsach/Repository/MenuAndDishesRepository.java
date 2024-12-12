package com.Demeshkan.Kyrsach.Repository;

import com.Demeshkan.Kyrsach.Model.MenuAndDishes;
import com.Demeshkan.Kyrsach.Model.Parents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MenuAndDishesRepository extends JpaRepository<MenuAndDishes, Integer> {
    @Query("select p from MenuAndDishes p where p.menu.menuID = ?1")
    List<MenuAndDishes> findByDishesMenu(Integer menuID);
}