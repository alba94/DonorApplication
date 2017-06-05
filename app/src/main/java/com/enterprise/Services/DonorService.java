package com.enterprise.Services;

import com.enterprise.requests.DonorCreateRequest;
import com.enterprise.requests.DonorSearchFilterRequest;
import com.enterprise.responses.Donor;

import java.util.List;

/**
 * Created by dlika on 6/5/2017.
 */

public interface DonorService {


    List<Donor> getAllDonors();

    List<Donor> search(DonorSearchFilterRequest request);

    Donor getDonorById(int id);

    Donor createDonor(DonorCreateRequest request);
}
