package com.Demeshkan.Kyrsach.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name = "ReplSchedule")
public class ReplSchedule {
    @Id
    @GeneratedValue(generator = "ReplScheduleGenerator")
    @SequenceGenerator(
            name = "ReplScheduleGenerator",
            sequenceName = "ReplScheduleSequence",
            initialValue = 1
    )
    private Integer replScheduleID;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Date DayDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "everyDayScheduleID", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private EveryDaySchedule everyDaySchedule;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "eventID", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Events events;

    @NotNull
    @JsonFormat(pattern="HH:mm:ss")
    private LocalTime StartTime;

    @NotNull
    @JsonFormat(pattern="HH:mm:ss")
    private LocalTime EndTime;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "groupsID", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Groups group;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employeeID", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Employee teacher;

    @NotNull
    private Boolean isDel;

    public Integer getReplScheduleID() {
        return replScheduleID;
    }

    public void setReplScheduleID(Integer replScheduleID) {
        this.replScheduleID = replScheduleID;
    }

    public Date getDayDate() {
        return DayDate;
    }

    public void setDayDate(Date dayDate) {
        DayDate = dayDate;
    }

    public EveryDaySchedule getEveryDaySchedule() {
        return everyDaySchedule;
    }

    public void setEveryDaySchedule(EveryDaySchedule everyDaySchedule) {
        this.everyDaySchedule = everyDaySchedule;
    }

    public Events getEvents() {
        return events;
    }

    public void setEvents(Events events) {
        this.events = events;
    }

    public LocalTime getStartTime() {
        return StartTime;
    }

    public void setStartTime(LocalTime startTime) {
        StartTime = startTime;
    }

    public LocalTime getEndTime() {
        return EndTime;
    }

    public void setEndTime(LocalTime endTime) {
        EndTime = endTime;
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

    public Boolean getDel() {
        return isDel;
    }

    public void setDel(Boolean del) {
        isDel = del;
    }
}
