package com.Demeshkan.Kyrsach.Model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "Events")
public class Events {
    @Id
    @GeneratedValue(generator = "EventsGenerator")
    @SequenceGenerator(
            name = "EventsIDGenerator",
            sequenceName = "EventsSequense",
            initialValue = 1
    )
    private Integer eventID;

    @NotBlank
    private String Name;

    public Integer getEventID() {
        return eventID;
    }

    public void setEventID(Integer eventID) {
        this.eventID = eventID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
