package com.example.apiSpotify.servicio;

import com.example.apiSpotify.dto.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

@Service
public class SpotifyServiceImp {//implements SpotifyService {
    private ResponseEntity<String> response;
    private final String codeVerifier = genenerateCodeVerifier();
    private final String codeChallenge = generateCodeChallenge(codeVerifier);

    private String clientId = "242e921a083f4dac8f1c47688b2f9682";
    private String redirectUri= "http://127.0.0.1:55000/callback";

    private final WebClient webClient;
    //private final WebClientImpl webClientImpl;
    private final RestTemplate restTemplate; // = new RestTemplate();

    public SpotifyServiceImp( RestTemplate restTemplate, WebClient webClient){ // WebClientImpl webClientImpl){
        this.webClient = webClient;
        this.restTemplate = restTemplate;
        //this.webClientImpl = webClientImpl;
    }

    @Value("spotify.url")
    private String url;

    // genera el codigo aleatorio de 64 bytes
    public String genenerateCodeVerifier() {
        return Base64.getUrlEncoder().withoutPadding().encodeToString(
                new SecureRandom().generateSeed(64));
    }

    // combierte el codigo aleatorio en un hash con codificador SHA-256
    public String generateCodeChallenge(String codeVerifier) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(codeVerifier.getBytes(StandardCharsets.US_ASCII));
            return Base64.getUrlEncoder().withoutPadding().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 no disponible", e);
        }
    }

    public String getCodeVerifier() {
        return codeVerifier;
    }

    public String getCodeChallenge() {
        return codeChallenge;
    }

    // http://127.0.0.1:55000/login
    // Iniciar sesión
    public void login(HttpServletResponse response) throws IOException{
        String url = UriComponentsBuilder.fromHttpUrl("https://accounts.spotify.com/authorize")
                .queryParam("response_type", "code")
                .queryParam("client_id", this.clientId)
                .queryParam("redirect_uri", this.redirectUri)
                .queryParam("code_challenge_method", "S256")
                .queryParam("code_challenge", getCodeChallenge())
                .queryParam("scope", "user-read-private")
                .build().toUriString();

        response.sendRedirect(url);
    }
    // La API nos lleva aquí
    public ResponseEntity<String> callback(@RequestParam String code) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.add("grant_type", "authorization_code");
        form.add("code", code);
        form.add("redirect_uri", this.redirectUri);
        form.add("client_id", this.clientId);
        form.add("code_verifier", getCodeVerifier());

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(form, headers);

        response = restTemplate.postForEntity(
                "https://accounts.spotify.com/api/token", request, String.class);

        return response;
    }

    // http://127.0.0.1:55000/getAlbum/"ID DEL ALBUM"
    public Album getAlbum(@PathVariable String id, String token) {
        // url del endpoint: https://api.spotify.com/v1/albums/{id}

        HttpHeaders headers = new HttpHeaders();
        // Acceso con token
        headers.setBearerAuth(token);
        headers.set("Accept", "application/json");
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        String url = "https://api.spotify.com/v1/albums/" + id;

        // Consegir info y mapearla en el objeto album
        ResponseEntity<Album> album = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                Album.class
        );

        getInfoArtists(album.getBody(),token);

        String infoAlbum = album.getBody().toString();

        System.out.println(infoAlbum.toString());

        return album.getBody();
    }

    // completar la info de un json con info completa de los artistas haciendo otra llamada
    public void getInfoArtists(Album album, String token) {
        String ids[] = new String[album.getArtists().size()];

        for (int i = 0; i < album.getArtists().size(); i++) {
            ids[i] = album.getArtists().get(i).getId();
        }

        album.getArtists().clear();
        for (int j = 0; j < ids.length; j++) {
            album.getArtists().add((getArtist(ids[j], token)));
        }
    }

    public Artist getArtist(@PathVariable String id, String token){
        HttpHeaders headers = new HttpHeaders();
        // Acceso con token
        headers.setBearerAuth(token);
        headers.set("Accept", "application/json");
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        String url = "https://api.spotify.com/v1/artists/" + id;

        // Consegir info y mapearla en el objeto artista
        ResponseEntity<Artist> artists = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                Artist.class
        );

        String infoArtist = artists.getBody().toString();

        System.out.println(infoArtist.toString());

        return artists.getBody();
    }

    public AlbumsArtists getAlbumsArtists(@PathVariable String id, String token){
        HttpHeaders headers = new HttpHeaders();
        // Acceso con token
        headers.setBearerAuth(token);
        headers.set("Accept", "application/json");
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        String url = "https://api.spotify.com/v1/artists/" + id + "/albums";

        ResponseEntity<AlbumsArtists> albums = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                AlbumsArtists.class
        );

        for (int i=0; i < albums.getBody().getItems().size(); i++){
            getInfoArtists(albums.getBody().getItems().get(i),token);
        }

        String infoArtist = albums.getBody().toString();

        System.out.println(infoArtist);

        return albums.getBody();
    }

    public User getInfoUser(@PathVariable String id, String token){
        HttpHeaders headers = new HttpHeaders();
        // Acceso con token
        headers.setBearerAuth(token);
        headers.set("Accept", "application/json");
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        String url = "https://api.spotify.com/v1/users/" + id ;

        ResponseEntity<User> user = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                User.class
        );

        String infoUser = user.getBody().toString();
        System.out.println(infoUser);

        return user.getBody();
    }


    public Items getInfoUserPlaylists(@PathVariable String id, String token){
        HttpHeaders headers = new HttpHeaders();
        // Acceso con token
        headers.setBearerAuth(token);
        headers.set("Accept", "application/json");
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        String url = "https://api.spotify.com/v1/users/" + id + "/playlists";

        ResponseEntity<Items> user = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                Items.class
        );

        String infoUser = user.getBody().toString();
        System.out.println(infoUser);

        return user.getBody();
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }


//     A PARTIR DE AQUÍ METODOS CON WEB CLIENT

    public Album getAlbumInfoWeb(String albumId, String token) {
        String uri = "https://api.spotify.com/v1/albums/" + albumId;
        Album album = new Album();
        Album responseAlbum = //(Album)webClientImpl.get(uri, token, album);
                webClient.get()
                .uri(uri)
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .bodyToMono(Album.class)
                .block();

        int sizeArtists = responseAlbum.getArtists().size();
        String idArtist[] = new String[responseAlbum.getArtists().size()];

        for (int i=0; i < sizeArtists; i++) {
            idArtist[i] = responseAlbum.getArtists().get(i).getId();
        }

        responseAlbum.getArtists().clear();
        for (int j=0; j< sizeArtists; j++){
            responseAlbum.getArtists().add(getArtistInfoWeb(idArtist[j], token));
        }

        return responseAlbum;
    }

    public Artist getArtistInfoWeb(String artistId, String token) {
        String uri = "https://api.spotify.com/v1/artists/" + artistId;
        return webClient.get()
                .uri(uri)
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .bodyToMono(Artist.class)
                .block();
    }



}
