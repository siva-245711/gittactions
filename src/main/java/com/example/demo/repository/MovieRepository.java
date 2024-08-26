package com.example.demo.repository;



import com.example.demo.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    // JpaRepository provides CRUD operations and more
}

