package com.us.junu.project.repository;

import com.us.junu.project.model.Donor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DonorRepository extends JpaRepository<Donor, Integer> {

    Optional<Donor> findAllByEmail(String email);
}
