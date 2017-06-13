package com.enterprise.responses;

import java.util.Date;

/**
 * Created by dlika on 6/5/2017.
 */

public class Donor {

    private Integer idblooddonor;

    private String address;

    private Date birthday;

    private String bllodtype;

    private Integer createdby;

    private Date createddate;

    private String email;

    private String lastname;

    private String name;

    private String personalnumber;

    private String phonenumber;

    private Integer validity;

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBllodtype() {
        return bllodtype;
    }

    public void setBllodtype(String bllodtype) {
        this.bllodtype = bllodtype;
    }

    public Integer getCreatedby() {
        return createdby;
    }

    public void setCreatedby(Integer createdby) {
        this.createdby = createdby;
    }

    public Date getCreateddate() {
        return createddate;
    }

    public void setCreateddate(Date createddate) {
        this.createddate = createddate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPersonalnumber() {
        return personalnumber;
    }

    public void setPersonalnumber(String personalnumber) {
        this.personalnumber = personalnumber;
    }

    public Integer getValidity() {
        return validity;
    }

    public void setValidity(Integer validity) {
        this.validity = validity;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Donor{" +
                "idblooddonor=" + idblooddonor +
                ", address='" + address + '\'' +
                ", birthday=" + birthday +
                ", bllodtype='" + bllodtype + '\'' +
                ", createdby=" + createdby +
                ", createddate=" + createddate +
                ", email='" + email + '\'' +
                ", lastname='" + lastname + '\'' +
                ", name='" + name + '\'' +
                ", personalnumber='" + personalnumber + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", validity=" + validity +
                ", city=" + city +
                '}';
    }

    private City city;

    public Integer getIdblooddonor() {
        return idblooddonor;
    }

    public void setIdblooddonor(Integer idblooddonor) {
        this.idblooddonor = idblooddonor;
    }
}
