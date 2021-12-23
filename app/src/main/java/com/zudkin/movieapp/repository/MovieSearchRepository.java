package com.zudkin.movieapp.repository;

import com.google.gson.Gson;
import com.zudkin.movieapp.model.Movie;
import com.zudkin.movieapp.model.Result;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class MovieSearchRepository {
    private List<Result> resultList;

    public MovieSearchRepository(String urlString){
        Thread thread = new Thread(() ->{
            try {
                URL url = new URL(urlString);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                Gson gson = new Gson();
                try(BufferedReader reader =
                            new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()))){
                    Movie movie = gson.fromJson(reader, Movie.class);
                    this.resultList = movie.getResults();
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

    public List<Result> getResultList() {
        return resultList;
    }

    @Override
    public String toString() {
        return "MovieSearchRepository{" +
                "resultList=" + resultList +
                '}';
    }
}
