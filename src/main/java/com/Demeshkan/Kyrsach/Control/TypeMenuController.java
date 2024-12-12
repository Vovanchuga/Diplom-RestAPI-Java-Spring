package com.Demeshkan.Kyrsach.Control;

import com.Demeshkan.Kyrsach.Model.TypeMenu;
import com.Demeshkan.Kyrsach.Model.Users;
import com.Demeshkan.Kyrsach.Repository.EmployeeRepository;
import com.Demeshkan.Kyrsach.Repository.TypeMenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/TypeMenu")
public class TypeMenuController {
    @Autowired
    private TypeMenuRepository typeMenuRepository;

    @GetMapping("/allTypeMenu")
    public ResponseEntity<List<TypeMenu>> getTypeMenu(){
        return ResponseEntity.ok(typeMenuRepository.findAll());
    }
}
