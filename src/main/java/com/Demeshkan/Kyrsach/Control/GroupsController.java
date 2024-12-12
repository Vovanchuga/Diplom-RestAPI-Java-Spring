package com.Demeshkan.Kyrsach.Control;

import com.Demeshkan.Kyrsach.Model.*;
import com.Demeshkan.Kyrsach.Repository.EmployeeRepository;
import com.Demeshkan.Kyrsach.Repository.GroupTypeRepository;
import com.Demeshkan.Kyrsach.Repository.GroupsRepository;
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
@RequestMapping("/Groups")
public class GroupsController {
    @Autowired
    private GroupsRepository groupsRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private GroupTypeRepository groupTypeRepository;

    @GetMapping("/allGroups")
    public ResponseEntity<List<Groups>> getGroups(){
        return ResponseEntity.ok(groupsRepository.findAll());
    }

    @GetMapping("/{groupId}")
    public ResponseEntity<Groups> getOneReplSchedule(@PathVariable Integer groupId, Pageable pageable){

        Groups groups = groupsRepository.findById(groupId)
                .orElseThrow(() -> new ResourceNotFoundException("Type not found with ID" + groupId));

        return new ResponseEntity<>(groups, HttpStatus.OK);
    }
    @GetMapping("/findByName")
    public Groups getSpecName(
            @RequestParam(name = "name") String Name,
            Pageable pageable){
        /*if (!usersRepository.existsById(replScheduleID)) {
            throw new ResourceNotFoundException("Not found Tutorial with id = " + replScheduleID);
        }

        //List<Users> users = usersRepository.findByUserType(typeId);
        return groupsRepository.findBytutor(replScheduleID, pageable);*/
        return groupsRepository.findByGroupName(Name);
    }
    @PostMapping("/createGroup/{groupTypeId}/GroupType")
    public Groups createReplSchedule (
            @Valid @RequestBody Groups groups,
            @PathVariable(name = "groupTypeId") Integer groupTypeId){
        GroupType groupType = groupTypeRepository.getReferenceById(groupTypeId);
        groups.setGroupType(groupType);
        /*return replScheduleRepository.findById(typeId)
                .map(type ->{
                    replSchedule.setUserType(type);
                    return usersRepository.save(replSchedule);
                }).orElseThrow(() -> new ResourceNotFoundException("Type not found with ID" + typeId));*/
        return groupsRepository.save(groups);
    }

    @PutMapping("/uploadGroup/{groupID}")
    public Groups updateReplSchedule(@PathVariable Integer groupID,
                                   @Valid @RequestBody Groups groups){
        return groupsRepository.findById(groupID)
                .map(rep ->{
                    rep.setName(groups.getName());
                    rep.setMaxCount(groups.getMaxCount());
                    rep.setCreateDate(groups.getCreateDate());
                    return groupsRepository.save(rep);
                }).orElseThrow(() -> new ResourceNotFoundException("ReplSchedule not found with ID" + groupID));
    }
    @DeleteMapping("/deleteGroup/{groupID}")
    public ResponseEntity<?> deleteReplSchedule(@PathVariable Integer groupID){
        return groupsRepository.findById(groupID)
                .map(usr ->{
                    groupsRepository.delete(usr);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("ReplSchedule not found with ID" + groupID));
    }
    @PostMapping("/addGroupEmployee/{groupId}/Group/{employeeId}/Employee")
    public ResponseEntity<Groups> createEventAndrecomendation(
            @Valid @RequestBody Groups group,
            @PathVariable Integer employeeId,
            @PathVariable Integer groupId){
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Type not found with ID" + employeeId));
        Groups groups = groupsRepository.findById(groupId)
                .orElseThrow(() -> new ResourceNotFoundException("Type not found with ID" + groupId));

        employee.addEmployeeGroup(groups);
        employeeRepository.save(employee);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteGroupEmployee/{menuId}/Group/{employeeId}/Employee")
    public ResponseEntity<HttpStatus> deleteUserFromrecomendation(@PathVariable Integer menuId, @PathVariable Integer employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Recomendation with id = " + menuId));

        employee.removeEmployeeGroup(menuId);
        employeeRepository.save(employee);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
