package com.Demeshkan.Kyrsach.Model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "TypeMenu")
public class TypeMenu {
    @Id
    @GeneratedValue(generator = "TypeMenuGenerator")
    @SequenceGenerator(
            name = "TypeMenuGenerator",
            sequenceName = "TypeMenuSequence",
            initialValue = 1
    )
    private Integer typeMenuID;

    @NotBlank
    private String Name;

    public Integer getTypeMenuID() {
        return typeMenuID;
    }

    public void setTypeMenuID(Integer typeMenuID) {
        this.typeMenuID = typeMenuID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
