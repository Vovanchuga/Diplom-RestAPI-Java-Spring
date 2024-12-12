package com.Demeshkan.Kyrsach.Control;

import com.Demeshkan.Kyrsach.Model.Dishes;
import com.Demeshkan.Kyrsach.Model.Employee;
import com.Demeshkan.Kyrsach.Model.Menu;
import com.Demeshkan.Kyrsach.Model.MenuAndDishes;
import com.Demeshkan.Kyrsach.Repository.DishesRepository;
import com.Demeshkan.Kyrsach.Repository.EmployeeRepository;
import com.Demeshkan.Kyrsach.Repository.MenuAndDishesRepository;
import com.Demeshkan.Kyrsach.Repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/MenuAndDishesController")
public class MenuAndDishesController {
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private DishesRepository dishesRepository;
    @Autowired
    private MenuAndDishesRepository menuAndDishesRepository;

    @GetMapping("/allMenuAndDishesController")
    public ResponseEntity<List<MenuAndDishes>> getMenu(){
        return ResponseEntity.ok(menuAndDishesRepository.findAll());
    }

    @GetMapping("/{menuID}/menu")
    public List<MenuAndDishes> getOneReplSchedule(@PathVariable Integer menuID){
        if (!menuRepository.existsById(menuID)) {
            throw new ResourceNotFoundException("Not found Menu with id = " + menuID);
        }

        /*List<Users> users = usersRepository.findByUserType(typeId);
        return groupsRepository.findBytutor(replScheduleID, pageable);*/
        return menuAndDishesRepository.findByDishesMenu(menuID);
    }

    @PostMapping("/createMenuDish/{menuId}/menu/{dishId}/dish")
    public MenuAndDishes createReplSchedule (
            @Valid @RequestBody MenuAndDishes menuAndDishes,
            @PathVariable(name = "menuId") Integer menuId,
            @PathVariable(name = "dishId") Integer dishId){
        Menu menu = menuRepository.getReferenceById(menuId);
        Dishes dishes = dishesRepository.getReferenceById(dishId);
        menuAndDishes.setMenu(menu);
        menuAndDishes.setDishes(dishes);
        /*return replScheduleRepository.findById(typeId)
                .map(type ->{
                    replSchedule.setUserType(type);
                    return usersRepository.save(replSchedule);
                }).orElseThrow(() -> new ResourceNotFoundException("Type not found with ID" + typeId));*/
        menuAndDishes.setId(null);
        return menuAndDishesRepository.save(menuAndDishes);
    }

    @PutMapping("/uploadMenuDish/{menuID}")
    public MenuAndDishes updateReplSchedule(@PathVariable Integer menuID,
                                   @Valid @RequestBody MenuAndDishes menu){
        return menuAndDishesRepository.findById(menuID)
                .map(rep ->{
                    rep.setDishes(menu.getDishes());
                    rep.setMenu(menu.getMenu());
                    rep.setCalories(menu.getCalories());
                    return menuAndDishesRepository.save(rep);
                }).orElseThrow(() -> new ResourceNotFoundException("MenuAndDishes not found with ID" + menuID));
    }
    @DeleteMapping("/deleteDish/{menuID}")
    public ResponseEntity<?> deleteReplSchedule(@PathVariable Integer menuID){
        return menuAndDishesRepository.findById(menuID)
                .map(usr ->{
                    menuAndDishesRepository.delete(usr);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("MenuAndDishes not found with ID" + menuID));
    }
}
