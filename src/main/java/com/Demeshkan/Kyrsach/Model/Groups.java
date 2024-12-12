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
@Table(name = "Groups")
public class Groups {
    @Id
    @GeneratedValue(generator = "GroupsGenerator")
    @SequenceGenerator(
            name = "GroupsIDGenerator",
            sequenceName = "GroupsSequense",
            initialValue = 1
    )
    private Integer groupsID;

    @NotBlank
    private String Name;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Date CreateDate;

    @NotNull
    private Integer MaxCount;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "groupTypeID", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private GroupType groupType;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "employeeAndGroups")
    private Set<Employee> groupsAndEmployee = new HashSet<>();

    public Integer getGroupsID() {
        return groupsID;
    }

    public void setGroupsID(Integer groupsID) {
        this.groupsID = groupsID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Integer getMaxCount() {
        return MaxCount;
    }

    public void setMaxCount(Integer maxCount) {
        MaxCount = maxCount;
    }

    public Set<Employee> getGroupsAndEmployee() {
        return groupsAndEmployee;
    }

    public void setGroupsAndEmployee(Set<Employee> groupsAndEmployee) {
        this.groupsAndEmployee = groupsAndEmployee;
    }

    public Date getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(Date createDate) {
        CreateDate = createDate;
    }

    public GroupType getGroupType() {
        return groupType;
    }

    public void setGroupType(GroupType groupType) {
        this.groupType = groupType;
    }

}
