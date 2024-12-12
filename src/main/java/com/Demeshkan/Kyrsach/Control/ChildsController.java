package com.Demeshkan.Kyrsach.Control;

import com.Demeshkan.Kyrsach.Model.*;
import com.Demeshkan.Kyrsach.Repository.ChildsRepository;
import com.Demeshkan.Kyrsach.Repository.DishesRepository;
import com.Demeshkan.Kyrsach.Repository.GroupsRepository;
import com.Demeshkan.Kyrsach.Repository.ParentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/Childs")
public class ChildsController {
    @Autowired
    private ChildsRepository childsRepository;
    @Autowired
    private ParentsRepository parentsRepository;
    @Autowired
    private GroupsRepository groupsRepository;

    @GetMapping("/allChilds")
    public ResponseEntity<List<Childs>> getChilds(){
        return ResponseEntity.ok(childsRepository.findAll());
    }

    @GetMapping("/{childId}")
    public ResponseEntity<Childs> getChild(@PathVariable Integer childId){

        Childs childs = childsRepository.findById(childId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID" + childId));
        return new ResponseEntity<>(childs, HttpStatus.OK);
    }

    @GetMapping("/groups/{groupsID}")
    public List<Childs> getChildsGroup(@PathVariable Integer groupsID){

        if (childsRepository.existsById(groupsID)) {
            throw new ResourceNotFoundException("Not found Groups with id = " + groupsID);
        }

        /*List<Users> users = usersRepository.findByUserType(typeId);
        return groupsRepository.findBytutor(replScheduleID, pageable);*/
        return childsRepository.findByChildsGroup(groupsID);
    }

    @PostMapping("/createChild/{groupsID}/group")
    public Childs createEmployee (
            @Valid @RequestBody Childs childs,
            @PathVariable(name = "groupsID") Integer professionId){
        Groups groups = groupsRepository.getReferenceById(professionId);
        childs.setGroup(groups);
        /*return replScheduleRepository.findById(typeId)
                .map(type ->{
                    replSchedule.setUserType(type);
                    return usersRepository.save(replSchedule);
                }).orElseThrow(() -> new ResourceNotFoundException("Type not found with ID" + typeId));*/
        childs.setChildID(null);
        return childsRepository.save(childs);
    }
    @PutMapping("/uploadChild/{childId}")
    public Childs updateEmployee(@PathVariable Integer childId,
                                   @Valid @RequestBody Childs childs){
        return childsRepository.findById(childId)
                .map(empl ->{
                    empl.setLastName(childs.getLastName());
                    empl.setFirstName(childs.getFirstName());
                    empl.setMiddleName(childs.getMiddleName());
                    empl.setHeight(childs.getHeight());
                    empl.setWeight(childs.getWeight());
                    empl.setGroup(childs.getGroup());
                    empl.setBirthDate(childs.getBirthDate());
                    empl.setGender(childs.getGender());
                    return childsRepository.save(empl);
                }).orElseThrow(() -> new ResourceNotFoundException("Child not found with ID" + childId));
    }
    @DeleteMapping("/deleteChild/{childId}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Integer childId){
        return childsRepository.findById(childId)
                .map(empl ->{
                    childsRepository.delete(empl);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Child not found with ID" + childId));
    }

    @PostMapping("/addGroupEmployee/{groupId}/Child/{employeeId}/Parent")
    public ResponseEntity<Childs> createEventAndrecomendation(
            @Valid @RequestBody Childs group,
            @PathVariable Integer employeeId,
            @PathVariable Integer groupId){
        Parents employee = parentsRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Type not found with ID" + employeeId));
        Childs groups = childsRepository.findById(groupId)
                .orElseThrow(() -> new ResourceNotFoundException("Type not found with ID" + groupId));

        groups.addEmployeeGroup(employee);
        childsRepository.save(groups);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteGroupEmployee/{menuId}/Parent/{employeeId}/Child")
    public ResponseEntity<HttpStatus> deleteUserFromrecomendation(@PathVariable Integer menuId, @PathVariable Integer employeeId) {
        Childs employee = childsRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Recomendation with id = " + employeeId));

        employee.removeEmployeeGroup(menuId);
        childsRepository.save(employee);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}/parents")
    public ResponseEntity<?> getParentsByChildId(@PathVariable Integer id) {
        Set<Parents> parents = findParentsByChildId(id);
        return ResponseEntity.ok(parents);
    }
    public Set<Parents> findParentsByChildId(Integer id) {
        Childs child = childsRepository.findById(id).orElseThrow();
        return child.getChildsAndParents();
    }
}
