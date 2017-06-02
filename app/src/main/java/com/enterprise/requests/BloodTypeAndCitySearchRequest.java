package com.enterprise.requests;

/**
 * Created by dlika on 6/2/2017.
 */

public class BloodTypeAndCitySearchRequest {


    private String bloodType;

    private String cityName;

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((bloodType == null) ? 0 : bloodType.hashCode());
        result = prime * result + ((cityName == null) ? 0 : cityName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BloodTypeAndCitySearchRequest other = (BloodTypeAndCitySearchRequest) obj;
        if (bloodType == null) {
            if (other.bloodType != null)
                return false;
        } else if (!bloodType.equals(other.bloodType))
            return false;
        if (cityName == null) {
            if (other.cityName != null)
                return false;
        } else if (!cityName.equals(other.cityName))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "BloodTypeAndCitySearch [bloodType=" + bloodType + ", cityName=" + cityName + "]";
    }

}
