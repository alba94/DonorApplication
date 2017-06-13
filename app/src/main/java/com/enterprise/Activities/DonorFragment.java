package com.enterprise.activities;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.enterprise.responses.Donor;
import com.enterprise.services.DonorService;

import org.robospring.RoboSpring;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DonorFragment extends Fragment {

    List<Donor> lista;

    @Autowired
    DonorService donorService;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RoboSpring.autowire(this);
        lista=this.donorService.getAllDonors();

    }
}
