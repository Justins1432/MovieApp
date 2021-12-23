
package com.zudkin.movieapp.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Movie implements Serializable {

    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("results")
    @Expose
    private List<Result> results = null;
    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;
    @SerializedName("total_results")
    @Expose
    private Integer totalResults;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Movie() {
    }

    /**
     * 
     * @param totalResults
     * @param totalPages
     * @param page
     * @param results
     */
    public Movie(Integer page, List<Result> results, Integer totalPages, Integer totalResults) {
        super();
        this.page = page;
        this.results = results;
        this.totalPages = totalPages;
        this.totalResults = totalResults;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return page.equals(movie.page)
                && results.equals(movie.results)
                && totalPages.equals(movie.totalPages)
                && totalResults.equals(movie.totalResults);
    }


    @Override
    public int hashCode() {
        return Objects.hash(page, results, totalPages, totalResults);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "page=" + page +
                ", results=" + results +
                ", totalPages=" + totalPages +
                ", totalResults=" + totalResults +
                '}';
    }

}
