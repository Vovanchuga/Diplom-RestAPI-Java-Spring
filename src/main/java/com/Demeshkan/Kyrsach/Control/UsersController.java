package com.Demeshkan.Kyrsach.Control;

import com.Demeshkan.Kyrsach.Model.Employee;
import com.Demeshkan.Kyrsach.Model.Parents;
import com.Demeshkan.Kyrsach.Model.ReplSchedule;
import com.Demeshkan.Kyrsach.Model.Users;
import com.Demeshkan.Kyrsach.Repository.EmployeeRepository;
import com.Demeshkan.Kyrsach.Repository.ParentsRepository;
import com.Demeshkan.Kyrsach.Repository.UsersRepository;
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
@RequestMapping("/Users")
public class UsersController {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private ParentsRepository parentsRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/allUsers")
    public ResponseEntity<List<Users>> getGroups(){
        return ResponseEntity.ok(usersRepository.findAll());
    }
    @GetMapping("/parent/{userID}")
    public Parents getParentUser(@PathVariable Integer userID){

        /*if (!parentsRepository.existsById(userID)) {
            throw new ResourceNotFoundException("Not found User with id = " + userID);
        }
        */
        /*List<Users> users = usersRepository.findByUserType(typeId);
        return groupsRepository.findBytutor(replScheduleID, pageable);*/
        return usersRepository.findByParentsUser(userID);
    }
    @GetMapping("/employee/{userID}")
    public Employee getEmployeeUser(@PathVariable Integer userID){

        /*if (!employeeRepository.existsById(userID)) {
            throw new ResourceNotFoundException("Not found User with id = " + userID);
        }*/

        /*List<Users> users = usersRepository.findByUserType(typeId);
        return groupsRepository.findBytutor(replScheduleID, pageable);*/
        return usersRepository.findByEmployeeUser(userID);
    }
    @GetMapping("/findByEmail")
    public Users getUserByEmail(
            @RequestParam(name = "email") String email){
        /*if (!usersRepository.existsById(replScheduleID)) {
            throw new ResourceNotFoundException("Not found Tutorial with id = " + replScheduleID);
        }

        //List<Users> users = usersRepository.findByUserType(typeId);
        return groupsRepository.findBytutor(replScheduleID, pageable);*/
        return usersRepository.findByEmail(email);
    }
    @GetMapping("/findByLogin")
    public Users getUserByLogin(
            @RequestParam(name = "login") String login){
        /*if (!usersRepository.existsById(replScheduleID)) {
            throw new ResourceNotFoundException("Not found Tutorial with id = " + replScheduleID);
        }

        //List<Users> users = usersRepository.findByUserType(typeId);
        return groupsRepository.findBytutor(replScheduleID, pageable);*/
        return usersRepository.findByLogin(login);
    }
    @PostMapping("/createUser")
    public Users createUser (@Valid @RequestBody Users users){
        /*return replScheduleRepository.findById(typeId)
                .map(type ->{
                    replSchedule.setUserType(type);
                    return usersRepository.save(replSchedule);
                }).orElseThrow(() -> new ResourceNotFoundException("Type not found with ID" + typeId));*/
        users.setUserID(null);
        return usersRepository.save(users);
    }
    @PutMapping("/uploadUser/{userID}")
    public Users updateUser(@PathVariable Integer userID,
                                   @Valid @RequestBody Users users){
        return usersRepository.findById(userID)
                .map(usr ->{
                    usr.setEmail(users.getEmail());
                    usr.setLogin(users.getLogin());
                    usr.setPassword(users.getPassword());
                    return usersRepository.save(usr);
                }).orElseThrow(() -> new ResourceNotFoundException("User not found with ID" + userID));
    }
    @DeleteMapping("/deleteUser/{userID}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer userID){
        return usersRepository.findById(userID)
                .map(usr ->{
                    usersRepository.delete(usr);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("User not found with ID" + userID));
    }
}

