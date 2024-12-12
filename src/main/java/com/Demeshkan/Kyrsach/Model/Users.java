package com.Demeshkan.Kyrsach.Model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "Users")
public class Users{
    @Id
    @GeneratedValue(generator = "UserGenerator")
    @SequenceGenerator(
            name = "UserGenerator",
            sequenceName = "UserSequence",
            initialValue = 1
    )
    private Integer userID;

    @NotBlank
    private String Email;

    @NotBlank
    private String Login;

    @NotBlank
    private String Password;

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String login) {
        Login = login;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
