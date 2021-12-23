package com.zudkin.movieapp.activities;


import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import com.zudkin.movieapp.R;
import com.zudkin.movieapp.database.MovieDatabaseHandler;
import com.zudkin.movieapp.download.DownLoadPosters;
import com.zudkin.movieapp.model.Result;

public class DetailMovie extends AppCompatActivity {
    public static final String MOVIE = "movie";

    private Result result;

    private TextView title, overview, vote_average, release_date;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        initComponents();
    }

    private void initComponents() {
        this.result = (Result) getIntent().getSerializableExtra(MOVIE);

        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        this.getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().
                getColor(R.color.navigation_app)));
        this.getSupportActionBar().setCustomView(R.layout.toolbar_title_detail_movie);

        this.getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_navigate_before_24);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initDetailMovieView();

    }

    private void initDetailMovieView() {
        this.title = findViewById(R.id.title_detail_movie);
        this.overview = findViewById(R.id.overview_movie);
        this.imageView = findViewById(R.id.poster_detail_movie);
        this.vote_average = findViewById(R.id.average_detail_movie);
        this.release_date = findViewById(R.id.release_detail_movie);

        this.title.setText(this.result.getTitle());
        this.overview.setText(this.result.getOverview());
        this.vote_average.setText(String.valueOf(this.result.getVoteAverage()));
        this.release_date.setText(this.result.getReleaseDate());

        DownLoadPosters downLoadPosters = new DownLoadPosters(this.imageView, 280, 200 );
        downLoadPosters.execute("https://image.tmdb.org/t/p/original" + this.result.getPosterPath());
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

    public void addMovieToFavourites(View view) {
        this.title = findViewById(R.id.title_detail_movie);
        this.overview = findViewById(R.id.overview_movie);
        this.vote_average = findViewById(R.id.average_detail_movie);
        this.release_date = findViewById(R.id.release_detail_movie);

        long resultId = getIntent().getLongExtra("resultId", -1);
        String title = this.result.getTitle();
        String release = this.result.getReleaseDate();
        Double average = this.result.getVoteAverage();
        String overview_movie = this.result.getOverview();
        String poster = this.result.getPosterPath();

        Result result = new Result(resultId, title, release, average, overview_movie, poster);

        MovieDatabaseHandler databaseHandler = new MovieDatabaseHandler(this);
        if (databaseHandler.addMovie(result)){
            finish();
            Toast.makeText(this, "Фильм успешно добавлен в избранные!", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Ошибка добавления фильма", Toast.LENGTH_SHORT).show();
        }

    }

}