package com.enterprise.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by dlika on 6/2/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class City {

    private Integer idcity;

    private String name;

    private Integer validity;

    public Integer getIdcity() {
        return idcity;
    }

    public void setIdcity(Integer idcity) {
        this.idcity = idcity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValidity() {
        return validity;
    }

    public void setValidity(Integer validity) {
        this.validity = validity;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idcity == null) ? 0 : idcity.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((validity == null) ? 0 : validity.hashCode());
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
        City other = (City) obj;
        if (idcity == null) {
            if (other.idcity != null)
                return false;
        } else if (!idcity.equals(other.idcity))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (validity == null) {
            if (other.validity != null)
                return false;
        } else if (!validity.equals(other.validity))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "City [idcity=" + idcity + ", name=" + name + ", validity=" + validity + "]";
    }
}
