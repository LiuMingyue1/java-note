package week2.day8.rest2.service;

import org.springframework.stereotype.Service;
import week2.day8.rest2.pojo.MovieDTO;
import java.util.*;

@Service
public interface MovieService {
    List<MovieDTO> getAllMovies();
}
