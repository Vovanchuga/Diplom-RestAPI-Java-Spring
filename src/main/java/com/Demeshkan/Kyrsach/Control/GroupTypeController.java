package com.Demeshkan.Kyrsach.Control;

import com.Demeshkan.Kyrsach.Model.GroupType;
import com.Demeshkan.Kyrsach.Model.Groups;
import com.Demeshkan.Kyrsach.Repository.EmployeeRepository;
import com.Demeshkan.Kyrsach.Repository.GroupTypeRepository;
import com.Demeshkan.Kyrsach.Repository.GroupsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/GroupType")
public class GroupTypeController {
    @Autowired
    private GroupsRepository groupsRepository;
    @Autowired
    private GroupTypeRepository groupTypeRepository;

    @GetMapping("/allGroupType")
    public ResponseEntity<List<GroupType>> getGroups(){
        return ResponseEntity.ok(groupTypeRepository.findAll());
    }
}
