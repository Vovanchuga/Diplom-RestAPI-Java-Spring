package com.Demeshkan.Kyrsach.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Employee")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Employee {
    @Id
    @GeneratedValue(generator = "EmployeeGenerator")
    @SequenceGenerator(
            name = "EmployeeGenerator",
            sequenceName = "EmployeeSequense",
            initialValue = 1
    )
    private Integer employeeID;

    @NotBlank
    private String LastName;

    @NotBlank
    private String FirstName;

    private String MiddleName;

    @NotNull
    private Integer PasSeries;

    @NotNull
    private Integer PasNumber;

    @NotBlank
    private String Address;

    @NotNull
    private Long Phone;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Date BirthDate;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Date WorkStartDate;

    @NotNull
    private Boolean Gender;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "professionID", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Professions profession;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userID", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Users user;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "employeeAndGroups",
            joinColumns = @JoinColumn(name = "employeeID"),
            inverseJoinColumns = @JoinColumn(name = "groupsID")
    )
    @JsonIgnore
    private Set<Groups> employeeAndGroups = new HashSet<>();

    public Integer getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Integer employeeID) {
        this.employeeID = employeeID;
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

    public Integer getPasSeries() {
        return PasSeries;
    }

    public void setPasSeries(Integer pasSeries) {
        PasSeries = pasSeries;
    }

    public Integer getPasNumber() {
        return PasNumber;
    }

    public void setPasNumber(Integer pasNumber) {
        PasNumber = pasNumber;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public Long getPhone() {
        return Phone;
    }

    public void setPhone(Long phone) {
        Phone = phone;
    }

    public Date getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(Date birthDate) {
        BirthDate = birthDate;
    }

    public Professions getProfession() {
        return profession;
    }

    public void setProfession(Professions profession) {
        this.profession = profession;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Set<Groups> getEmployeeAndGroups() {
        return employeeAndGroups;
    }

    public void setEmployeeAndGroups(Set<Groups> employeeAndGroups) {
        this.employeeAndGroups = employeeAndGroups;
    }

    public Date getWorkStartDate() {
        return WorkStartDate;
    }

    public void setWorkStartDate(Date workStartDate) {
        WorkStartDate = workStartDate;
    }

    public Boolean getGender() {
        return Gender;
    }

    public void setGender(Boolean gender) {
        Gender = gender;
    }

    public void addEmployeeGroup(Groups groups) {
        this.employeeAndGroups.add(groups);
        groups.getGroupsAndEmployee().add(this);
    }

    public void removeEmployeeGroup(Integer groupId) {
        Groups groups = this.employeeAndGroups.stream().filter(u -> u.getGroupsID() == groupId).findFirst().orElse(null);
        if (groups != null) {
            this.employeeAndGroups.remove(groups);
            groups.getGroupsAndEmployee().remove(this);
        }
    }
}
