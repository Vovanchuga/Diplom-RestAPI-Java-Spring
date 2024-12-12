package com.Demeshkan.Kyrsach.Control;

import com.Demeshkan.Kyrsach.Model.Employee;
import com.Demeshkan.Kyrsach.Model.Parents;
import com.Demeshkan.Kyrsach.Model.Professions;
import com.Demeshkan.Kyrsach.Model.Users;
import com.Demeshkan.Kyrsach.Repository.ParentsRepository;
import com.Demeshkan.Kyrsach.Repository.ProfessionsRepository;
import com.Demeshkan.Kyrsach.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/Parents")
public class ParentsController {
    @Autowired
    private ParentsRepository parentsRepository;
    @Autowired
    private UsersRepository usersRepository;

    @GetMapping("/allParents")
    public ResponseEntity<List<Parents>> getParents(){
        return ResponseEntity.ok(parentsRepository.findAll());
    }

    @GetMapping("/{parentsId}")
    public ResponseEntity<Parents> getParent(@PathVariable Integer parentsId){

        Parents parents = parentsRepository.findById(parentsId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID" + parentsId));
        return new ResponseEntity<>(parents, HttpStatus.OK);
    }

    @PostMapping("/createParent/{userId}/user")
    public Parents createParent (
            @Valid @RequestBody Parents parents,
            @PathVariable(name = "userId") Integer userId){
        Users user = usersRepository.getReferenceById(userId);
        parents.setUser(user);
        parents.setParentsID(null);
        return parentsRepository.save(parents);
    }
    @PutMapping("/uploadParent/{parentsId}")
    public Parents updateParent(@PathVariable Integer parentsId,
                                   @Valid @RequestBody Parents parents){
        return parentsRepository.findById(parentsId)
                .map(pr ->{
                    pr.setLastName(parents.getLastName());
                    pr.setFirstName(parents.getFirstName());
                    pr.setMiddleName(parents.getMiddleName());
                    pr.setAddress(parents.getAddress());
                    pr.setPasSeries(parents.getPasSeries());
                    pr.setPasNumber(parents.getPasNumber());
                    pr.setBirthDate(parents.getBirthDate());
                    pr.setGender(parents.getGender());
                    pr.setPhone(parents.getPhone());
                    return parentsRepository.save(pr);
                }).orElseThrow(() -> new ResourceNotFoundException("Parent not found with ID" + parentsId));
    }
    @DeleteMapping("/deleteParent/{parentsId}")
    public ResponseEntity<?> deleteParent(@PathVariable Integer parentsId){
        return parentsRepository.findById(parentsId)
                .map(pr ->{
                    parentsRepository.delete(pr);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Parent not found with ID" + parentsId));
    }
}
