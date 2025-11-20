package week2.day8.rest2.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import week2.day8.rest2.pojo.MovieResponseDTO;

@Configuration
public class RestTemplateConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

//    public static void main(String[] args) {
//        RestTemplate restTemplate = new RestTemplate();
//        MovieResponseDTO movieResponseDTO = restTemplate.getForObject("https://jsonmock.hackerrank.com/api/moviesdata/search/", MovieResponseDTO.class);
//        System.out.println(movieResponseDTO);
//    }
}
