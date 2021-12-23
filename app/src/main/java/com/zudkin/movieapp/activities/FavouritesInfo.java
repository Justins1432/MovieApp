package com.zudkin.movieapp.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zudkin.movieapp.R;
import com.zudkin.movieapp.database.MovieDatabaseHandler;
import com.zudkin.movieapp.download.DownLoadPosters;
import com.zudkin.movieapp.model.Result;

import java.util.List;

public class FavouritesInfo extends AppCompatActivity {
    public static final String FAVOURITE_INFO = "favourite_info";

    private Result result;

    private ImageView imageView;
    private TextView title, average, overview, release;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites_info);
        initComponents();
    }

    private void initComponents(){
        this.result = (Result) getIntent().getSerializableExtra(FAVOURITE_INFO);
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        this.getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().
                getColor(R.color.navigation_app)));
        initTextView();
    }

    private void initTextView(){
        this.title = findViewById(R.id.title_favourites_info_movie);
        this.average = findViewById(R.id.average_favourites_info_movie);
        this.overview = findViewById(R.id.overview_favourites_info_movie);
        this.release = findViewById(R.id.release_favourites_info_movie);
        this.imageView = findViewById(R.id.poster_favourites_info_movie);

        this.title.setText(this.result.getTitle());
        this.average.setText(String.valueOf(this.result.getVoteAverage()));
        this.overview.setText(this.result.getOverview());
        this.release.setText(this.result.getReleaseDate());

        DownLoadPosters posters = new DownLoadPosters(this.imageView, 280, 200);
        posters.execute("https://image.tmdb.org/t/p/original" + this.result.getPosterPath());

    }

    public void deleteFavouritesMovie(View view) {
        long idFavourite = getIntent().getLongExtra("idFavourite", -1);
        MovieDatabaseHandler handler = new MovieDatabaseHandler(this);
        handler.deleteMovie(idFavourite);
        Toast.makeText(this, "Фильм успешно удалён из избранных", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void closeActivity(View view) {
        finish();
    }
}