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
@Table(name = "Parents")
public class Parents {
    @Id
    @GeneratedValue(generator = "ParentsGenerator")
    @SequenceGenerator(
            name = "ParentsIDGenerator",
            sequenceName = "ParentsSequense",
            initialValue = 1
    )
    private Integer parentsID;

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
    private Boolean Gender;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userID", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Users user;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "childsAndParents")
    private Set<Childs> parentsAndChilds = new HashSet<>();

    public Integer getParentsID() {
        return parentsID;
    }

    public void setParentsID(Integer parentsID) {
        this.parentsID = parentsID;
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

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Boolean getGender() {
        return Gender;
    }

    public void setGender(Boolean gender) {
        Gender = gender;
    }

    public Set<Childs> getParentsAndChilds() {
        return parentsAndChilds;
    }

    public void setParentsAndChilds(Set<Childs> parentsAndChilds) {
        this.parentsAndChilds = parentsAndChilds;
    }
}
