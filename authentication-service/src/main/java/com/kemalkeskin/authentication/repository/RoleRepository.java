package com.kemalkeskin.authentication.repository;

import com.kemalkeskin.authentication.model.Role;
import com.kemalkeskin.authentication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Integer> {

    Optional<Role> findByRole(String role);

}
