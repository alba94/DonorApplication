package com.enterprise.requests;

/**
 * Created by dlika on 6/4/2017.
 */

import java.util.Date;

public class DonorUpdateRequest {

    private String address;
    private Date birthday;
    private String bllodtype;
    private String email;
    private String lastname;
    private String name;
    private String personalnumber;
    private String phonenumber;
    private int cityId;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getBllodtype() {
        return bllodtype;
    }

    public void setBllodtype(String bllodtype) {
        this.bllodtype = bllodtype;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

}