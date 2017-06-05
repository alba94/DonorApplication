package com.enterprise.responses;

import java.util.Date;

/**
 * Created by dlika on 6/5/2017.
 */

public class Donation {


    private Integer iddonation;

    private String bloodtype;

    private Integer createdby;

    private Date donateddate;

    private Integer isavailable;

    private Date useddate;

    public String getBloodtype() {
        return bloodtype;
    }

    public void setBloodtype(String bloodtype) {
        this.bloodtype = bloodtype;
    }

    public Integer getIddonation() {
        return iddonation;
    }

    public void setIddonation(Integer iddonation) {
        this.iddonation = iddonation;
    }

    public Integer getCreatedby() {
        return createdby;
    }

    public void setCreatedby(Integer createdby) {
        this.createdby = createdby;
    }

    public Date getDonateddate() {
        return donateddate;
    }

    public void setDonateddate(Date donateddate) {
        this.donateddate = donateddate;
    }

    public Integer getIsavailable() {
        return isavailable;
    }

    public void setIsavailable(Integer isavailable) {
        this.isavailable = isavailable;
    }

    public Date getUseddate() {
        return useddate;
    }

    public void setUseddate(Date useddate) {
        this.useddate = useddate;
    }
}
