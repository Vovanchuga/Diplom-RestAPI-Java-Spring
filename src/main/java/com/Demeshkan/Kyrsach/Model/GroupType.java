package com.Demeshkan.Kyrsach.Model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "GroupType")
public class GroupType {
    @Id
    @GeneratedValue(generator = "GroupTypeGenerator")
    @SequenceGenerator(
            name = "GroupTypeIDGenerator",
            sequenceName = "GroupTypeSequense",
            initialValue = 1
    )
    private Integer groupTypeID;

    @NotBlank
    private String Name;

    public Integer getGroupTypeID() {
        return groupTypeID;
    }

    public void setGroupTypeID(Integer groupTypeID) {
        this.groupTypeID = groupTypeID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
