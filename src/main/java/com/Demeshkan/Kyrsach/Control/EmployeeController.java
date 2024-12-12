package com.Demeshkan.Kyrsach.Control;

import com.Demeshkan.Kyrsach.Model.*;
import com.Demeshkan.Kyrsach.Repository.EmployeeRepository;
import com.Demeshkan.Kyrsach.Repository.ProfessionsRepository;
import com.Demeshkan.Kyrsach.Repository.UsersRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/Employee")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private ProfessionsRepository professionsRepository;

    @GetMapping("/allEmployee")
    public ResponseEntity<List<Employee>> getEmployee(){
        return ResponseEntity.ok(employeeRepository.findAll());
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<Employee> getUser(@PathVariable Integer employeeId, Pageable pageable){

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID" + employeeId));
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @GetMapping("/profession/{professionID}")
    public List<Employee> getEmployeeUser(@PathVariable Integer professionID){

        if (!employeeRepository.existsById(professionID)) {
            throw new ResourceNotFoundException("Not found Profession with id = " + professionID);
        }

        /*List<Users> users = usersRepository.findByUserType(typeId);
        return groupsRepository.findBytutor(replScheduleID, pageable);*/
        return employeeRepository.findByEmployeeProfession(professionID);
    }

    @PostMapping("/createEmployee/{userId}/user/{professionId}/profession")
    public Employee createEmployee (
            @Valid @RequestBody Employee employee,
            @PathVariable(name = "userId") Integer userId,
            @PathVariable(name = "professionId") Integer professionId){
        Users user = usersRepository.getReferenceById(userId);
        Professions professions = professionsRepository.getReferenceById(professionId);
        employee.setUser(user);
        employee.setProfession(professions);
        /*return replScheduleRepository.findById(typeId)
                .map(type ->{
                    replSchedule.setUserType(type);
                    return usersRepository.save(replSchedule);
                }).orElseThrow(() -> new ResourceNotFoundException("Type not found with ID" + typeId));*/
        employee.setEmployeeID(null);
        return employeeRepository.save(employee);
    }
    @PutMapping("/uploadEmployee/{employeeId}")
    public Employee updateEmployee(@PathVariable Integer employeeId,
                                 @Valid @RequestBody Employee employee){
        return employeeRepository.findById(employeeId)
                .map(empl ->{
                    empl.setLastName(employee.getLastName());
                    empl.setFirstName(employee.getFirstName());
                    empl.setMiddleName(employee.getMiddleName());
                    empl.setProfession(employee.getProfession());
                    empl.setAddress(employee.getAddress());
                    empl.setPasSeries(employee.getPasSeries());
                    empl.setPasNumber(employee.getPasNumber());
                    empl.setBirthDate(employee.getBirthDate());
                    empl.setWorkStartDate(employee.getWorkStartDate());
                    empl.setGender(employee.getGender());
                    empl.setPhone(employee.getPhone());
                    return employeeRepository.save(empl);
                }).orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID" + employeeId));
    }
    @DeleteMapping("/deleteEmployee/{employeeId}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Integer employeeId){
        return employeeRepository.findById(employeeId)
                .map(empl ->{
                    employeeRepository.delete(empl);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID" + employeeId));
    }
}
