package com.example.MicroService.Repository;

import com.example.MicroService.bo.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMovieRepository extends JpaRepository<Movie, Long> {


}
