package com.Demeshkan.Kyrsach.Control;

import com.Demeshkan.Kyrsach.Model.*;
import com.Demeshkan.Kyrsach.Repository.EmployeeRepository;
import com.Demeshkan.Kyrsach.Repository.EventsRepository;
import com.Demeshkan.Kyrsach.Repository.EveryDayScheduleRepository;
import com.Demeshkan.Kyrsach.Repository.GroupsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/EveryDaySchedule")
public class EveryDayScheduleController {
    @Autowired
    private EveryDayScheduleRepository everyDayScheduleRepository;
    @Autowired
    private EventsRepository eventsRepository;

    @Autowired
    private GroupsRepository groupsRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/allEveryDaySchedule")
    public ResponseEntity<List<EveryDaySchedule>> getEveryDaySchedule(){
        return ResponseEntity.ok(everyDayScheduleRepository.findAll());
    }

    @GetMapping("/{groupsID}/group")
    public List<EveryDaySchedule> getEveryDayScheduleGroup(@PathVariable Integer groupsID){

        if (!groupsRepository.existsById(groupsID)) {
            throw new ResourceNotFoundException("Not found Group with id = " + groupsID);
        }

        /*List<Users> users = usersRepository.findByUserType(typeId);
        return groupsRepository.findBytutor(replScheduleID, pageable);*/
        return everyDayScheduleRepository.findByGroup(groupsID);
    }
    @PostMapping("/createSchedule/{eventId}/event/{groupId}/group/{teacherId}/teacher")
    public EveryDaySchedule createEveryDaySchedule (
            @Valid @RequestBody EveryDaySchedule everyDaySchedule,
            @PathVariable(name = "eventId") Integer eventId,
            @PathVariable(name = "groupId") Integer groupId,
            @PathVariable(name = "teacherId") Integer teacherId){
        Events event = eventsRepository.getReferenceById(eventId);
        Groups group = groupsRepository.getReferenceById(groupId);
        Employee teacher = employeeRepository.getReferenceById(teacherId);
        everyDaySchedule.setEvents(event);
        everyDaySchedule.setGroup(group);
        everyDaySchedule.setTeacher(teacher);
        /*return replScheduleRepository.findById(typeId)
                .map(type ->{
                    replSchedule.setUserType(type);
                    return usersRepository.save(replSchedule);
                }).orElseThrow(() -> new ResourceNotFoundException("Type not found with ID" + typeId));*/
        return everyDayScheduleRepository.save(everyDaySchedule);
    }
    @PutMapping("/uploadEveryDaySchedule/{everyDayScheduleId}")
    public EveryDaySchedule updateEveryDaySchedule(@PathVariable Integer everyDayScheduleId,
                                           @Valid @RequestBody EveryDaySchedule everyDaySchedule){
        return everyDayScheduleRepository.findById(everyDayScheduleId)
                .map(upl ->{
                    upl.setTeacher(everyDaySchedule.getTeacher());
                    upl.setGroup(everyDaySchedule.getGroup());
                    upl.setEvents(everyDaySchedule.getEvents());
                    upl.setEndTime(everyDaySchedule.getEndTime());
                    upl.setStartTime(everyDaySchedule.getStartTime());
                    return everyDayScheduleRepository.save(upl);
                }).orElseThrow(() -> new ResourceNotFoundException("ReplSchedule not found with ID" + everyDayScheduleId));
    }
    @DeleteMapping("/deleteUser/{everyDayScheduleId}")
    public ResponseEntity<?> deleteReplSchedule(@PathVariable Integer everyDayScheduleId){
        return everyDayScheduleRepository.findById(everyDayScheduleId)
                .map(del ->{
                    everyDayScheduleRepository.delete(del);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("ReplSchedule not found with ID" + everyDayScheduleId));
    }
}
