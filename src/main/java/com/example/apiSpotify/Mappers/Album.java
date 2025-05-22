package com.example.apiSpotify.Mappers;
import static com.jayway.jsonpath.JsonPath.parse;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Album {

    @JsonProperty("name")
    private String name;

    @JsonProperty("artists")
    private List<Artists> artists;

    @JsonProperty("release_date")
    private String releaseDate;

    @JsonProperty("total_tracks")
    private Integer total_tracks;

    //private String nombreArtista;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTotal_tracks() {
        return total_tracks;
    }

    public void setTotal_tracks(Integer total_tracks) {
        this.total_tracks = total_tracks;
    }

    public List<Artists> getArtists() {
        return artists;
    }

    public void setArtists(List<Artists> artists) {
        this.artists = artists;
    }

//    public String getNombreArtista() {
//        return nombreArtista;
//    }
//
//    public void setNombreArtista(String nombreArtista) {
//        this.nombreArtista = nombreArtista;
//    }



    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return "Album{" + "\n" +
                "nombre= " + name + '\n' +
                "artista= " + artists.toString()+ '\n' +
                "fecha salida= " + releaseDate + '\n' +
                "canciones= " + total_tracks +
                '}';
    }
}