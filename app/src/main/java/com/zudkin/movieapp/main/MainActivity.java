package com.zudkin.movieapp.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.zudkin.movieapp.R;
import com.zudkin.movieapp.activities.AppInformation;
import com.zudkin.movieapp.activities.DeveloperInformation;
import com.zudkin.movieapp.activities.FavouritesMovie;
import com.zudkin.movieapp.activities.SearchMovie;
import com.zudkin.movieapp.adapter.MovieAdapter;
import com.zudkin.movieapp.model.Result;
import com.zudkin.movieapp.repository.MovieRepository;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerViewMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
    }

    private void initComponents() {
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        this.getSupportActionBar().setCustomView(R.layout.toolbar_title_layout);
        this.getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().
                getColor(R.color.navigation_app)));
        this.getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_account_circle_24);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initRecyclerViewMovies();
    }

    private void initRecyclerViewMovies() {
        this.recyclerViewMovies = findViewById(R.id.recycler_view_movies);
        this.recyclerViewMovies.setLayoutManager(new LinearLayoutManager(this));

        try {
            MovieRepository movieRepository = new MovieRepository();

            List<Result> results = movieRepository.getResults();
            if(results == null){
                Toast.makeText(this, "Не удалось загрузить....", Toast.LENGTH_SHORT).show();
                return;
            }
            this.recyclerViewMovies.setAdapter(new MovieAdapter(this, results));
        } catch (Exception e) {
            Toast.makeText(this, "Не удалось загрузить....", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_app, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_search_movie:
                Intent intent = new Intent(MainActivity.this, SearchMovie.class);
                startActivity(intent);
                break;

            case R.id.menu_settings:
                break;

            case R.id.infoApp:
                Intent intentAppInform = new Intent(MainActivity.this, AppInformation.class);
                startActivity(intentAppInform);
                break;

            case R.id.favouriteMovie:
                Intent intent_favourite = new Intent(MainActivity.this, FavouritesMovie.class);
                startActivity(intent_favourite);
                break;

            case android.R.id.home:
                Intent intent_developer = new Intent(MainActivity.this, DeveloperInformation.class);
                startActivity(intent_developer);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}