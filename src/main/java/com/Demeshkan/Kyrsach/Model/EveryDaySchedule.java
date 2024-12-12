package com.Demeshkan.Kyrsach.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@Entity
@Table(name = "EveryDaySchedule")
public class EveryDaySchedule {
    @Id
    @GeneratedValue(generator = "EveryDayScheduleGenerator")
    @SequenceGenerator(
            name = "EveryDayScheduleIDGenerator",
            sequenceName = "EveryDayScheduleSequense",
            initialValue = 1
    )
    private Integer everyDayScheduleID;

    @NotNull
    private Integer WeekDay;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "eventID", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Events events;

    @NotNull
    @JsonFormat(pattern="HH:mm:ss")
    private LocalTime startTime;

    @NotNull
    @JsonFormat(pattern="HH:mm:ss")
    private LocalTime endTime;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "groupsID", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Groups group;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employeeID", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Employee teacher;

    public Integer getEveryDayScheduleID() {
        return everyDayScheduleID;
    }

    public void setEveryDayScheduleID(Integer everyDayScheduleID) {
        this.everyDayScheduleID = everyDayScheduleID;
    }

    public Integer getWeekDay() {
        return WeekDay;
    }

    public void setWeekDay(Integer weekDay) {
        WeekDay = weekDay;
    }

    public Events getEvents() {
        return events;
    }

    public void setEvents(Events events) {
        this.events = events;
    }



    public Groups getGroup() {
        return group;
    }

    public void setGroup(Groups group) {
        this.group = group;
    }

    public Employee getTeacher() {
        return teacher;
    }

    public void setTeacher(Employee teacher) {
        this.teacher = teacher;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }
}
