package com.Demeshkan.Kyrsach.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Childs")
public class Childs {
    @Id
    @GeneratedValue(generator = "ChildsGenerator")
    @SequenceGenerator(
            name = "ChildsIDGenerator",
            sequenceName = "ChildsSequense",
            initialValue = 1
    )
    private Integer childID;

    @NotBlank
    private String LastName;

    @NotBlank
    private String FirstName;

    private String MiddleName;

    @NotNull
    private Integer Height;

    @NotNull
    private Integer Weight;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Date BirthDate;

    @NotNull
    private Boolean Gender;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "groupsID", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Groups group;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "childsAndParents",
            joinColumns = @JoinColumn(name = "childID"),
            inverseJoinColumns = @JoinColumn(name = "parentsID")
    )
    @JsonIgnore
    private Set<Parents> childsAndParents= new HashSet<>();

    public Integer getChildID() {
        return childID;
    }

    public void setChildID(Integer childID) {
        this.childID = childID;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getMiddleName() {
        return MiddleName;
    }

    public void setMiddleName(String middleName) {
        MiddleName = middleName;
    }

    public Integer getHeight() {
        return Height;
    }

    public void setHeight(Integer height) {
        Height = height;
    }

    public Integer getWeight() {
        return Weight;
    }

    public void setWeight(Integer weight) {
        Weight = weight;
    }

    public Date getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(Date birthDate) {
        BirthDate = birthDate;
    }

    public Groups getGroup() {
        return group;
    }

    public void setGroup(Groups group) {
        this.group = group;
    }

    public Boolean getGender() {
        return Gender;
    }

    public void setGender(Boolean gender) {
        Gender = gender;
    }

    public Set<Parents> getChildsAndParents() {
        return childsAndParents;
    }

    public void setChildsAndParents(Set<Parents> childsAndParents) {
        this.childsAndParents = childsAndParents;
    }

    public void addEmployeeGroup(Parents groups) {
        this.childsAndParents.add(groups);
        groups.getParentsAndChilds().add(this);
    }

    public void removeEmployeeGroup(Integer groupId) {
        Parents groups = this.childsAndParents.stream().filter(u -> u.getParentsID() == groupId).findFirst().orElse(null);
        if (groups != null) {
            this.childsAndParents.remove(groups);
            groups.getParentsAndChilds().remove(this);
        }
    }
}
