package com.example.sakila.repositories;

import com.example.sakila.entities.Actor;
import com.example.sakila.entities.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface StaffRepository extends JpaRepository<Staff, Short> {
    List<Staff> findByFullNameContainingIgnoreCase(String firstName);
}
