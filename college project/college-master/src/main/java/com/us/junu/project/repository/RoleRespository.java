package com.us.junu.project.repository;

import com.us.junu.project.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRespository extends JpaRepository<Role, Integer> {

    Role findByRole(String role);
}
