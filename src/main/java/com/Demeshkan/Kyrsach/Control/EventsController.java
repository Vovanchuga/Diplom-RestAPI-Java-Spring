package com.Demeshkan.Kyrsach.Control;

import com.Demeshkan.Kyrsach.Model.Events;
import com.Demeshkan.Kyrsach.Model.ReplSchedule;
import com.Demeshkan.Kyrsach.Repository.EmployeeRepository;
import com.Demeshkan.Kyrsach.Repository.EventsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Events")
public class EventsController {
    @Autowired
    private EventsRepository eventsRepository;

    @GetMapping("/allEvents")
    public ResponseEntity<List<Events>> getEvents(){
        return ResponseEntity.ok(eventsRepository.findAll());
    }
}
