package com.example.demo.endpoint;

import com.example.demo.Model.Movie;
import com.example.demo.Model.Movies;
import com.example.demo.service.ActorService;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonParser;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@RestController
public class Controller {

    private static final String SECOND_SERVICE = "secondService";
    private static final Logger LOGGER = LoggerFactory.getLogger(Controller.class);

    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @CircuitBreaker(name = SECOND_SERVICE, fallbackMethod = "fooFallBack")
    @GetMapping("/findAll")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> getAllMovie() throws ParseException, JsonProcessingException {
       LOGGER.info("I'm trying to call ServiceOne");
       String responseAllMovies = restTemplate.getForObject("http://"+System.getenv("movieService")+"/movies",String.class);

        ActorService actorService = new ActorService();
        ObjectMapper mapper = new ObjectMapper();
        responseAllMovies = "{\"movies\":" + responseAllMovies + "}";
        Movies movies = mapper.readValue(responseAllMovies, Movies.class);

        for (Movie movie : movies){
            actorService.bind(movie);
        }

        LOGGER.info("ServiceOne has responded");
        return new ResponseEntity<>(movies.toString(),HttpStatus.OK);
    }

    private ResponseEntity<String> fooFallBack(Exception e) throws JsonProcessingException {
        LOGGER.error("ServiceOne has not responded, I'm calling Fallback");

       Movie movie = new Movie(0,"FallBack Method", "C'est l'histoire de deux étudiants qui essayèrent d'implémenter un fallBack et apparemment cela a fonctioné ;D");
       ActorService actorService = new ActorService();
       actorService.bind(movie);


        LOGGER.error(e.getMessage());
        return new ResponseEntity<>(movie.toString(), HttpStatus.SERVICE_UNAVAILABLE);
    }
}
