package com.example.demo.Model;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Movies implements Iterable<Movie>{

    private List<Movie> movies = new ArrayList<>();


    @Override
    public String toString() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(this.movies);
            return json;
        } catch (Exception e) {
            return "[]";
        }
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public Iterator<Movie> iterator() {
        return this.movies.iterator();
    }
}
