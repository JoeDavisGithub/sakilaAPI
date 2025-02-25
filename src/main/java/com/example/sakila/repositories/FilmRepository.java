package com.example.sakila.repositories;

import com.example.sakila.entities.Actor;
import com.example.sakila.entities.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface FilmRepository extends JpaRepository<Film, Short> {
    List<Film> findByTitleContainingIgnoreCase(String title);
}
