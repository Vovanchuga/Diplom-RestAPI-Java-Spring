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
@Table(name = "Menu")
public class Menu {
    @Id
    @GeneratedValue(generator = "MenuGenerator")
    @SequenceGenerator(
            name = "MenuGenerator",
            sequenceName = "MenuSequence",
            initialValue = 1
    )
    private Integer menuID;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Date MenuDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "typeMenuID", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private TypeMenu typeMenu;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employeeID", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Employee cook;

    public Integer getMenuID() {
        return menuID;
    }

    public void setMenuID(Integer menuID) {
        this.menuID = menuID;
    }

    public Date getMenuDate() {
        return MenuDate;
    }

    public void setMenuDate(Date menuDate) {
        MenuDate = menuDate;
    }

    public TypeMenu getTypeMenu() {
        return typeMenu;
    }

    public void setTypeMenu(TypeMenu typeMenu) {
        this.typeMenu = typeMenu;
    }

    public Employee getCook() {
        return cook;
    }

    public void setCook(Employee cook) {
        this.cook = cook;
    }

}
