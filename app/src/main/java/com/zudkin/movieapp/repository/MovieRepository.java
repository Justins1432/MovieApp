package com.zudkin.movieapp.repository;

import com.google.gson.Gson;
import com.zudkin.movieapp.model.Movie;
import com.zudkin.movieapp.model.Result;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class MovieRepository {
    private List<Result> results;

    public MovieRepository(){
        Thread thread = new Thread(() -> {
            try {
                URL url =
                        new URL("https://api.themoviedb.org/3/movie/popular?api_key=57a5540deefb424e87c1582ddecaae46&language=ru-RU");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                Gson gson = new Gson();
                try(BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()))){
                    Movie movie = gson.fromJson(reader, Movie.class);
                    this.results = movie.getResults();
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

    public List<Result> getResults() { return results; }

    @Override
    public String toString() {
        return "MovieRepository{" +
                "results=" + results +
                '}';
    }

}
