package com.Demeshkan.Kyrsach.Model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "Professions")
public class Professions {
    @Id
    @GeneratedValue(generator = "ProfessionGenerator")
    @SequenceGenerator(
            name = "ProfessionGenerator",
            sequenceName = "ProfessionSequence",
            initialValue = 1
    )
    private Integer professionID;

    @NotBlank
    private String Name;

    @NotBlank
    private Boolean Admin;

    @NotBlank
    private Boolean Cook;

    public Integer getProfessionID() {
        return professionID;
    }

    public void setProfessionID(Integer professionID) {
        this.professionID = professionID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Boolean getAdmin() {
        return Admin;
    }

    public void setAdmin(Boolean admin) {
        Admin = admin;
    }

    public Boolean getCook() {
        return Cook;
    }

    public void setCook(Boolean cook) {
        Cook = cook;
    }
}
