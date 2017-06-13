package com.enterprise.requests;

/**
 * Created by dlika on 6/5/2017.
 */

public class DonorSearchFilterRequest {

    private int bloodTypeId;

    private int cityId;

    private String cardId;

    public int getBloodTypeId() {
        return bloodTypeId;
    }

    public void setBloodTypeId(int bloodTypeId) {
        this.bloodTypeId = bloodTypeId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }
}