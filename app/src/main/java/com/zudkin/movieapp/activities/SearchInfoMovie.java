package com.zudkin.movieapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zudkin.movieapp.R;
import com.zudkin.movieapp.database.MovieDatabaseHandler;
import com.zudkin.movieapp.download.DownLoadPosters;
import com.zudkin.movieapp.model.Result;

public class SearchInfoMovie extends AppCompatActivity {
    public static final String SEARCH_INFO_MOVIE = "search_info_movie";

    private Result result;

    private ImageView imageView;
    private TextView title, overview, average, data_release;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_info_movie);
        initComponents();
    }

    private void initComponents() {
        this.result = (Result) getIntent().getSerializableExtra(SEARCH_INFO_MOVIE);

        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        this.getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().
                getColor(R.color.navigation_app)));

        this.getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_navigate_before_24);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initTextView();
    }

    private void initTextView(){
        this.title = findViewById(R.id.title_search_movie);
        this.overview = findViewById(R.id.overview_search_movie);
        this.average = findViewById(R.id.average_search_movie);
        this.imageView = findViewById(R.id.poster_search_info_movie);
        this.data_release = findViewById(R.id.data_release_search_movie);

        this.title.setText(this.result.getTitle());
        this.overview.setText(this.result.getOverview());
        this.average.setText(String.valueOf(this.result.getVoteAverage()));
        this.data_release.setText(this.result.getReleaseDate());

        DownLoadPosters downLoadPosters = new DownLoadPosters(this.imageView, 280, 200);
        downLoadPosters.execute("https://image.tmdb.org/t/p/original" + this.result.getPosterPath());
    }

    public void addMovieToFavourites(View view) {
        this.title = findViewById(R.id.title_search_movie);
        this.overview = findViewById(R.id.overview_search_movie);
        this.average = findViewById(R.id.average_search_movie);
        this.imageView = findViewById(R.id.poster_search_info_movie);
        this.data_release = findViewById(R.id.data_release_search_movie);

        long resultId = getIntent().getLongExtra("resultId", -1);
        String title = this.result.getTitle();
        String overview_movie = this.result.getOverview();
        Double average = this.result.getVoteAverage();
        String release = this.result.getReleaseDate();
        String poster = this.result.getPosterPath();

        Result result = new Result(resultId, title, release, average, overview_movie, poster);

        MovieDatabaseHandler handler = new MovieDatabaseHandler(this);
        if (handler.addMovie(result)){
            finish();
            Toast.makeText(this, "Фильм успешно добавлен в избранные!", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Ошибка добавления фильма!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_search_info, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }


}