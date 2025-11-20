package week2.day8.rest2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import week2.day8.rest2.pojo.MovieDTO;
import week2.day8.rest2.service.MovieService;

import java.util.*;

@Controller
@ResponseBody
@RequestMapping("/movie")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<List<MovieDTO>> getAllMovie() {
        List<MovieDTO> movieDTOS = movieService.getAllMovies();
        return new ResponseEntity<>(movieDTOS, HttpStatus.OK);
    }
}

/**
 * T1                           T2
 * stack1                       stack2
 *
 *
 * getAllMovies(service)        getAllMovies(service)
 * getAllMovie(controller)      getAllMovies(controller)
 */