package com.enterprise.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by dlika on 6/15/2017.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    private int userid;

    private String name;

    private String surname;

    private String username;

    private String email;

    private Integer centerid;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getCenterid() {
        return centerid;
    }

    public void setCenterid(Integer centerid) {
        this.centerid = centerid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "userid=" + userid +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", centerid=" + centerid +
                '}';
    }



}