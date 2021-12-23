package com.zudkin.movieapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.zudkin.movieapp.R;
import com.zudkin.movieapp.adapter.FavouritesAdapter;
import com.zudkin.movieapp.database.MovieDatabaseHandler;
import com.zudkin.movieapp.model.Result;

import java.util.List;

public class FavouritesMovie extends AppCompatActivity {
    public static final String MOVIE_FAVOURITES = "movie_favourites";
    private RecyclerView recyclerViewFavourites;

    private List<Result> results;
    private FavouritesAdapter favouritesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites_movie);
        initComponents();
    }

    private void initComponents(){

        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        this.getSupportActionBar().setCustomView(R.layout.toolbar_title_favourites_movie);
        this.getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().
                getColor(R.color.navigation_app)));
        this.getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_navigate_before_24);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initRecyclerViewFavourites();
    }

    private void initRecyclerViewFavourites(){
        this.recyclerViewFavourites = findViewById(R.id.recycler_view_favourites);
        this.recyclerViewFavourites.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        MovieDatabaseHandler databaseHandler = new MovieDatabaseHandler(this);
        results = databaseHandler.getResults();
        favouritesAdapter = new FavouritesAdapter(results, this);
        this.recyclerViewFavourites.setAdapter(favouritesAdapter);
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