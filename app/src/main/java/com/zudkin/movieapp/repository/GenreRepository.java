package com.zudkin.movieapp.repository;

import com.google.gson.Gson;
import com.zudkin.movieapp.model.Genre;
import com.zudkin.movieapp.model.Genres;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class GenreRepository {
    private List<Genre> genres;

    public GenreRepository(){
        Thread thread = new Thread(() ->{
            try {
                URL url =
                        new URL("https://api.themoviedb.org/3/genre/movie/list?api_key=57a5540deefb424e87c1582ddecaae46");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                Gson gson = new Gson();
                try(BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()))){
                    Genres genres1 = gson.fromJson(reader, Genres.class);
                    this.genres = genres1.getGenres();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Genre> getGenres() {
        return genres;
    }

    @Override
    public String toString() {
        return "GenreRepository{" +
                "genres=" + genres +
                '}';
    }
}
