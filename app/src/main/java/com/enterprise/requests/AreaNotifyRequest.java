package com.enterprise.requests;

/**
 * Created by dlika on 6/5/2017.
 */

public class AreaNotifyRequest {
    private String cityName;

    private String bloodType;


    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }
}
