package com.Demeshkan.Kyrsach.Control;

import com.Demeshkan.Kyrsach.Model.*;
import com.Demeshkan.Kyrsach.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/Menu")
public class MenuController {
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private DishesRepository dishesRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private TypeMenuRepository typeMenuRepository;

    @GetMapping("/allMenu")
    public ResponseEntity<List<Menu>> getMenu(){
        return ResponseEntity.ok(menuRepository.findAll());
    }

    @GetMapping("/{menuID}")
    public ResponseEntity<Menu> getOneReplSchedule(@PathVariable Integer menuID, Pageable pageable){

        Menu menu = menuRepository.findById(menuID)
                .orElseThrow(() -> new ResourceNotFoundException("Type not found with ID" + menuID));

        return new ResponseEntity<>(menu, HttpStatus.OK);
    }

    @GetMapping("/{cookId}/cook/{typeMenuID}/typeMenu/findByDate")
    public Menu getReplScheduleByDateDay(
            @RequestParam(name = "date")@DateTimeFormat(pattern = "yyyy-MM-dd") Date dayData,
            @PathVariable(name = "cookId") Integer cookId,
            @PathVariable(name = "typeMenuID") Integer typeMenuID) {
        /*if (!usersRepository.existsById(replScheduleID)) {
            throw new ResourceNotFoundException("Not found Tutorial with id = " + replScheduleID);
        }

        //List<Users> users = usersRepository.findByUserType(typeId);
        return groupsRepository.findBytutor(replScheduleID, pageable);*/
        return menuRepository.findBySpecDayDate(dayData,typeMenuID,cookId);
    }

    @GetMapping("/findByDate")
    public List<Menu> getSpecMenu(
            @RequestParam(name = "date")@DateTimeFormat(pattern = "yyyy-MM-dd") Date dayData,
            Pageable pageable){
        /*if (!usersRepository.existsById(replScheduleID)) {
            throw new ResourceNotFoundException("Not found Tutorial with id = " + replScheduleID);
        }

        //List<Users> users = usersRepository.findByUserType(typeId);
        return groupsRepository.findBytutor(replScheduleID, pageable);*/
        return menuRepository.findByDayDate(dayData);
    }

    @PostMapping("/createMenu/{cookId}/cook/{typeMenuID}/typeMenu")
    public Menu createReplSchedule (
            @Valid @RequestBody Menu menu,
            @PathVariable(name = "cookId") Integer teacherId,
            @PathVariable(name = "typeMenuID") Integer typeMenuID){
        Employee teacher = employeeRepository.getReferenceById(teacherId);
        TypeMenu typeMenu = typeMenuRepository.getReferenceById(typeMenuID);
        menu.setCook(teacher);
        menu.setTypeMenu(typeMenu);
        /*return replScheduleRepository.findById(typeId)
                .map(type ->{
                    replSchedule.setUserType(type);
                    return usersRepository.save(replSchedule);
                }).orElseThrow(() -> new ResourceNotFoundException("Type not found with ID" + typeId));*/
        return menuRepository.save(menu);
    }

    @PutMapping("/uploadMenu/{menuID}")
    public Menu updateReplSchedule(@PathVariable Integer menuID,
                                           @Valid @RequestBody Menu menu){
        return menuRepository.findById(menuID)
                .map(rep ->{
                    rep.setCook(menu.getCook());
                    rep.setTypeMenu(menu.getTypeMenu());
                    rep.setMenuDate(menu.getMenuDate());
                    return menuRepository.save(rep);
                }).orElseThrow(() -> new ResourceNotFoundException("ReplSchedule not found with ID" + menuID));
    }
    @DeleteMapping("/deleteMenu/{menuID}")
    public ResponseEntity<?> deleteReplSchedule(@PathVariable Integer menuID){
        return menuRepository.findById(menuID)
                .map(usr ->{
                    menuRepository.delete(usr);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("ReplSchedule not found with ID" + menuID));
    }
}
