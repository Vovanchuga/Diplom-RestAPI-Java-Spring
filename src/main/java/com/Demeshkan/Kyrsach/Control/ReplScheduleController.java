package com.Demeshkan.Kyrsach.Control;

import com.Demeshkan.Kyrsach.Model.*;
import com.Demeshkan.Kyrsach.Repository.*;
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
@RequestMapping("/ReplSchedule")
public class ReplScheduleController {
    @Autowired
    private ReplScheduleRepository replScheduleRepository;

    @Autowired
    private EveryDayScheduleRepository everyDayScheduleRepository;

    @Autowired
    private EventsRepository eventsRepository;

    @Autowired
    private GroupsRepository groupsRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/allReplSchedule")
    public ResponseEntity<Page<ReplSchedule>> getGroups(Pageable pageable){
        return ResponseEntity.ok(replScheduleRepository.findAll(pageable));
    }

    @GetMapping("/replSchedule/{replScheduleID}")
    public ResponseEntity<ReplSchedule> getOneReplSchedule(@PathVariable Integer replScheduleID, Pageable pageable){

        ReplSchedule replSchedule = replScheduleRepository.findById(replScheduleID)
                .orElseThrow(() -> new ResourceNotFoundException("Type not found with ID" + replScheduleID));

        return new ResponseEntity<>(replSchedule, HttpStatus.OK);
    }

    @GetMapping("/findByDate")
    public List<ReplSchedule> getReplScheduleByDateDay(
            @RequestParam(name = "date")@DateTimeFormat(pattern = "yyyy-MM-dd") Date dayData,
            Pageable pageable){
        /*if (!usersRepository.existsById(replScheduleID)) {
            throw new ResourceNotFoundException("Not found Tutorial with id = " + replScheduleID);
        }

        //List<Users> users = usersRepository.findByUserType(typeId);
        return groupsRepository.findBytutor(replScheduleID, pageable);*/
        return replScheduleRepository.findByDayDate(dayData);
    }

    @PostMapping("/createSchedule/{everyDayScheduleId}/everyDaySchedule/{eventId}/event/{groupId}/group/{teacherId}/teacher")
    public ReplSchedule createReplSchedule (
            @Valid @RequestBody ReplSchedule replSchedule,
            @PathVariable(name = "everyDayScheduleId") Integer everyDayScheduleId,
            @PathVariable(name = "eventId") Integer eventId,
            @PathVariable(name = "groupId") Integer groupId,
            @PathVariable(name = "teacherId") Integer teacherId){
        EveryDaySchedule everyDaySchedule = everyDayScheduleRepository.getReferenceById(everyDayScheduleId);
        Events event = eventsRepository.getReferenceById(eventId);
        Groups group = groupsRepository.getReferenceById(groupId);
        Employee teacher = employeeRepository.getReferenceById(teacherId);
        replSchedule.setEveryDaySchedule(everyDaySchedule);
        replSchedule.setEvents(event);
        replSchedule.setGroup(group);
        replSchedule.setTeacher(teacher);
        /*return replScheduleRepository.findById(typeId)
                .map(type ->{
                    replSchedule.setUserType(type);
                    return usersRepository.save(replSchedule);
                }).orElseThrow(() -> new ResourceNotFoundException("Type not found with ID" + typeId));*/
        return replScheduleRepository.save(replSchedule);
    }

    @PostMapping("/createSchedule/{eventId}/event/{groupId}/group/{teacherId}/teacher")
    public ReplSchedule createReplScheduleWO (
            @Valid @RequestBody ReplSchedule replSchedule,
            @PathVariable(name = "eventId") Integer eventId,
            @PathVariable(name = "groupId") Integer groupId,
            @PathVariable(name = "teacherId") Integer teacherId){
        Events event = eventsRepository.getReferenceById(eventId);
        Groups group = groupsRepository.getReferenceById(groupId);
        Employee teacher = employeeRepository.getReferenceById(teacherId);
        replSchedule.setEvents(event);
        replSchedule.setGroup(group);
        replSchedule.setTeacher(teacher);
        /*return replScheduleRepository.findById(typeId)
                .map(type ->{
                    replSchedule.setUserType(type);
                    return usersRepository.save(replSchedule);
                }).orElseThrow(() -> new ResourceNotFoundException("Type not found with ID" + typeId));*/
        return replScheduleRepository.save(replSchedule);
    }
    @PutMapping("/uploadReplSchedule/{replScheduleID}")
    public ReplSchedule updateReplSchedule(@PathVariable Integer replScheduleID,
                                   @Valid @RequestBody ReplSchedule replSchedule){
        return replScheduleRepository.findById(replScheduleID)
                .map(rep ->{
                    rep.setTeacher(replSchedule.getTeacher());
                    rep.setGroup(replSchedule.getGroup());
                    rep.setEvents(replSchedule.getEvents());
                    rep.setEveryDaySchedule(replSchedule.getEveryDaySchedule());
                    rep.setEndTime(replSchedule.getEndTime());
                    rep.setStartTime(replSchedule.getStartTime());
                    rep.setDayDate(replSchedule.getDayDate());
                    rep.setDel(replSchedule.getDel());
                    return replScheduleRepository.save(rep);
                }).orElseThrow(() -> new ResourceNotFoundException("ReplSchedule not found with ID" + replScheduleID));
    }
    @DeleteMapping("/deleteReplSchedule/{replScheduleID}")
    public ResponseEntity<?> deleteReplSchedule(@PathVariable Integer replScheduleID){
        return replScheduleRepository.findById(replScheduleID)
                .map(usr ->{
                    replScheduleRepository.delete(usr);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("ReplSchedule not found with ID" + replScheduleID));
    }
}
