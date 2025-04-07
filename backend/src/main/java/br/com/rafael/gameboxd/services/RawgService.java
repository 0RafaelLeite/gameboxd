package br.com.rafael.gameboxd.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class RawgService {

    @Value("${rawg.api.key}")
    private String apiKey;

    @Value("${rawg.api.base-url}")
    private String baseUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public String getGames(String search, Integer page, Integer pagesize){
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl + "/games")
                .queryParam("key", apiKey)
                .queryParam("search", search)
                .queryParam("page", page)
                .queryParam("pagesize", pagesize);

        //Include functions to get platforms and genres

        return restTemplate.getForObject(builder.toUriString(), String.class);
    }
}
