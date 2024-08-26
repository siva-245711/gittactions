package com.example.demo.service;

import com.example.demo.model.Movie;
import com.example.demo.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Map<String, Object> getPopularMoviesResponse() {
        List<Movie> movies = getAllMovies();
        Map<String, Object> response = new HashMap<>();
        response.put("results", movies);
        return response;
    }
}
