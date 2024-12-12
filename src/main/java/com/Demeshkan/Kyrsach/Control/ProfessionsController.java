package com.Demeshkan.Kyrsach.Control;

import com.Demeshkan.Kyrsach.Model.Professions;
import com.Demeshkan.Kyrsach.Model.TypeMenu;
import com.Demeshkan.Kyrsach.Repository.ProfessionsRepository;
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
@RequestMapping("/Professions")
public class ProfessionsController {
    @Autowired
    private ProfessionsRepository professionsRepository;

    @GetMapping("/allProfessions")
    public ResponseEntity<List<Professions>> getProfessions(){
        return ResponseEntity.ok(professionsRepository.findAll());
    }
}
