package com.example.demo.controller;

import com.example.demo.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/popular")
    public ResponseEntity<Map<String, Object>> getPopularMovies() {
        Map<String, Object> response = movieService.getPopularMoviesResponse();
        return ResponseEntity.ok(response);
    }
}
