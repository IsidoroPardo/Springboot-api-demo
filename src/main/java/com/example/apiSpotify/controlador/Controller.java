package com.example.apiSpotify.controlador;

import com.example.apiSpotify.dto.*;
import com.example.apiSpotify.servicio.SpotifyServiceImp;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;


@RestController
public class Controller {

    private final WebClient webClient = WebClient.create();
    private final SpotifyServiceImp serviceImp; // = new SpotifyServiceImp(webClient);
    private final RestTemplate restTemplate = new RestTemplate();

    public Controller(SpotifyServiceImp spotifyServiceImp){
        this.serviceImp = spotifyServiceImp;
    }

    @GetMapping("/login")
    public void login(HttpServletResponse response) throws IOException {
        serviceImp.login(response);
    }

    @GetMapping("/callback")
    public ResponseEntity<String> callback(@RequestParam String code) {
        return serviceImp.callback(code);
    }

    @GetMapping("getAlbum/{id}")
    public ResponseEntity<Album> getAlbum(@PathVariable String id, @RequestHeader(name = "token") String token){
        return new ResponseEntity<Album>(serviceImp.getAlbum(id, token), HttpStatus.OK) ;
    }

    @GetMapping("getArtista/{id}")
    public ResponseEntity<Artist> getArtist(@PathVariable String id, @RequestHeader(name = "token") String token){
        return new ResponseEntity<>(serviceImp.getArtist(id, token), HttpStatus.OK) ;
    }

    @GetMapping("getAlbumsArtist/{id}")
    public ResponseEntity<AlbumsArtists> getAlbumsArtist(@PathVariable String id, @RequestHeader(name = "token") String token){
        return new ResponseEntity<>(serviceImp.getAlbumsArtists(id, token), HttpStatus.OK) ;
    }

    @GetMapping("getInfoUser/{id}")
    public ResponseEntity<User> getUserInfo(@PathVariable String id, @RequestHeader(name="token") String token){
        return new ResponseEntity<>(serviceImp.getInfoUser(id, token), HttpStatus.OK);
    }

    @GetMapping("getInfoUserPlaylists/{id}")
    public ResponseEntity<Items> getUserInfoPlaylists(@PathVariable String id, @RequestHeader(name="token") String token){
        return new ResponseEntity<>(serviceImp.getInfoUserPlaylists(id, token), HttpStatus.OK);
    }

    @GetMapping("getAlbumInfoWeb/{id}")
    public ResponseEntity<Album> getAlbumInfoWeb(@PathVariable String id, @RequestHeader(name = "token") String token){
        return ResponseEntity.ok(serviceImp.getAlbumInfoWeb(id, token));
    }

    @GetMapping("getArtistInfoWeb/{id}")
    public ResponseEntity<Artist> getArtistInfoWeb(@PathVariable String id, @RequestHeader(name = "token") String token){
        return ResponseEntity.ok(serviceImp.getArtistInfoWeb(id, token));
    }

}