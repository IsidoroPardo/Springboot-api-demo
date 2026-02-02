package com.example.apiSpotify.servicio;

import com.example.apiSpotify.dto.Album;
import com.example.apiSpotify.dto.Artist;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

public interface SpotifyService {

    String generaCodeVerifier();
    String generaCodeChallenge(String codeVerifier);
    String getCodeVerifier();
    String getCodeChallenge();
    void login(HttpServletResponse response) throws IOException;
    ResponseEntity<String>callback(@RequestParam String code);
    Album getAlbum(@PathVariable String id, String token);
    Artist getArtists(@PathVariable String id, String token);
}
