package br.pucminas.model;

/**
 * Created by master on 03/11/16.
 */
public class JwtUser {
    private String userName;
    private String role;

    public JwtUser(){
    }

    public JwtUser(String userName, String role) {
        this();
        this.userName = userName;
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
