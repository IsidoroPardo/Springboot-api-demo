package com.example.apiSpotify.repositorio;

import com.example.apiSpotify.dto.Album;
import com.example.apiSpotify.dto.Artist;

public interface WebClient extends org.springframework.web.reactive.function.client.WebClient {
    public Object get(String uri, String token, Object entity);


}
