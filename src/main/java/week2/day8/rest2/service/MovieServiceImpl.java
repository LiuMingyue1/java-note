package week2.day8.rest2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import week2.day8.rest2.pojo.MovieDTO;
import week2.day8.rest2.pojo.MovieResponseDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService{

    private final RestTemplate restTemplate;

    @Value("${movie.url}")
    private String movieURL;

    @Autowired
    public MovieServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<MovieDTO> getAllMovies() {
        MovieResponseDTO movieResponseDTO = restTemplate.getForObject(movieURL, MovieResponseDTO.class);
        int total_page = movieResponseDTO.getTotal_pages();
        List<MovieDTO> res1 = movieResponseDTO.getData();
        List<CompletableFuture<List<MovieDTO>>> cfList = new ArrayList<>();
        for(int page = 2; page <= total_page; page++) {
            CompletableFuture<List<MovieDTO>> cf = fetchMovieByPage(page);
            cfList.add(cf);
        }
        List<MovieDTO> res2 = CompletableFuture.allOf(cfList.toArray(new CompletableFuture[0]))
                .thenApply(VOID -> cfList.stream().map(CompletableFuture::join).flatMap(List::stream).collect(Collectors.toList()))
                .join();
        res2.addAll(res1);
        return res2;
    }

    private CompletableFuture<List<MovieDTO>> fetchMovieByPage(final int page) {
        return CompletableFuture.supplyAsync(() -> restTemplate.getForObject(movieURL + "?page=" + page, MovieResponseDTO.class).getData());
    }
}
