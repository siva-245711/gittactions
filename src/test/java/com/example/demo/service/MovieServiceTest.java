package com.example.demo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class MovieServiceTest {

    private MovieService movieService;

    @BeforeEach
    public void setUp() {
        movieService = new MovieService();
    }

    @Test
    public void testGetPopularMovies() {
        List<String> movies = movieService.getPopularMovies();
        assertNotNull(movies);
        assertEquals(4, movies.size());
        assertTrue(movies.contains("Inception"));
        assertTrue(movies.contains("Interstellar"));
    }
}
