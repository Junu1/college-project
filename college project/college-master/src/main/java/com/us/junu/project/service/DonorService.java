package com.us.junu.project.service;

import com.us.junu.project.model.Donor;
import com.us.junu.project.resource.DonorDto;

import java.util.List;
import java.util.Optional;

public interface DonorService {


    void addDonor(DonorDto patient);

    List<Donor> findAllDonor();


    Optional<Donor> findById(int id);

    void updateDonor(Donor donor) throws Exception;

    Optional<Donor> findByEmail(String email);
}
