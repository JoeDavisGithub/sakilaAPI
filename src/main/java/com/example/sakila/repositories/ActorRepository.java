package com.example.sakila.repositories;

import com.example.sakila.entities.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface ActorRepository extends JpaRepository<Actor, Short> {
    List<Actor> findByFullNameContainingIgnoreCase(String firstName);
}
