package com.zudkin.movieapp.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Genres implements Serializable {

    @SerializedName("genres")
    @Expose
    private List<Genre> genres = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Genres() {
    }

    /**
     * 
     * @param genres
     */
    public Genres(List<Genre> genres) {
        super();
        this.genres = genres;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genres genres1 = (Genres) o;
        return genres.equals(genres1.genres);
    }

    @Override
    public int hashCode() {
        return Objects.hash(genres);
    }

    @Override
    public String toString() {
        return "Genres{" +
                "genres=" + genres +
                '}';
    }
}
