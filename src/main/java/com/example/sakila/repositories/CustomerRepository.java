package com.example.sakila.repositories;

import com.example.sakila.entities.Actor;
import com.example.sakila.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Short> {
    List<Customer> findByFullNameContainingIgnoreCase(String firstName);
}
