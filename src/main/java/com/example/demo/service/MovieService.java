package com.example.demo.service;

import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;

@Service
public class MovieService {

    public List<String> getPopularMovies() {
        // Mock data for popular movies
        return Arrays.asList("Inception", "Interstellar", "The Dark Knight", "Memento");
    }
}
