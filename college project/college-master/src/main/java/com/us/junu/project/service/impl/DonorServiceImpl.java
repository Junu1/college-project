package com.us.junu.project.service.impl;


import com.us.junu.project.model.Donor;
import com.us.junu.project.model.User;
import com.us.junu.project.repository.DonorRepository;
import com.us.junu.project.repository.UserRepository;
import com.us.junu.project.resource.DonorDto;
import com.us.junu.project.service.DonorService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DonorServiceImpl implements DonorService {

    private final DonorRepository donorRepository;
    private final UserRepository userRepository;

    public DonorServiceImpl(DonorRepository patientRepository, UserRepository userRepository) {
        this.donorRepository = patientRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void addDonor(DonorDto donerDto) {
        donorRepository.save(convertToPatient(donerDto));
    }

    private Donor convertToPatient(DonorDto donerDto) {
        Donor donor = new Donor();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(auth.getName());

        if (user.getMiddlename() != null) {
            donor.setFullName(user.getFirstname() + " " + user.getMiddlename() + " " + user.getLastname());
        } else {
            donor.setFullName(user.getFirstname() + " " + user.getLastname());
        }
        donor.setAddress(donerDto.getAddress());
        donor.setAge(donerDto.getAge());
        donor.setContactNumber(donerDto.getContactNumber());
        donor.setGender(donerDto.getGender());
        donor.setGroup(donerDto.getGroup());
        donor.setEmail(user.getEmail());
        return donorRepository.save(donor);
    }


    @Override
    public List<Donor> findAllDonor() {
        return donorRepository.findAll();
    }


    @Override
    public Optional<Donor> findById(int id) {
        return donorRepository.findById(id);
    }

    @Override
    public void updateDonor(Donor donor) throws Exception {

    }

    @Override
    public Optional<Donor> findByEmail(String email) {
        return donorRepository.findAllByEmail(email);
    }

}
