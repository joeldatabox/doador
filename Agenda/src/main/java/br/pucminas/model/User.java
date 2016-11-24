package br.pucminas.model;

import java.io.Serializable;

/**
 * Created by master on 03/11/16.
 */
public class User implements Serializable {
    public static final String ROLE_ADMIN = "ADM";
    public static final String ROLE_USER = "USR";

    private String userName;
    private String passWord;
    private String email;
    private String description;
    private String role;
    private Boolean isActivated;
    private Boolean isAdmin;

    public User() {
    }

    public User(String userName, String passWord, String email, String description, String role, Boolean isActivated, Boolean isAdmin) {
        this();
        this.userName = userName;
        this.passWord = passWord;
        this.email = email;
        this.description = description;
        this.role = role;
        this.isActivated = isActivated;
        this.isAdmin = isAdmin;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Boolean getActivated() {
        return isActivated;
    }

    public void setActivated(Boolean activated) {
        isActivated = activated;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", email='" + email + '\'' +
                ", description='" + description + '\'' +
                ", role='" + role + '\'' +
                ", isActivated=" + isActivated +
                ", isAdmin=" + isAdmin +
                '}';
    }
}
