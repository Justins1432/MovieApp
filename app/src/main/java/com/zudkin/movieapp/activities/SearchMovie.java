package com.zudkin.movieapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.zudkin.movieapp.R;
import com.zudkin.movieapp.adapter.MovieSearchAdapter;
import com.zudkin.movieapp.model.Movie;
import com.zudkin.movieapp.model.Result;
import com.zudkin.movieapp.repository.MovieSearchRepository;

import java.util.List;

public class SearchMovie extends AppCompatActivity {

    private RecyclerView recyclerViewSearchMovie;
    private MovieSearchAdapter searchAdapter;
    private List<Result> results;

    private EditText editTextTitleMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_movie);
        initComponents();

        this.editTextTitleMovie = findViewById(R.id.editTextSearch);

        this.editTextTitleMovie.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchMovies();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void initComponents() {
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        this.getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().
                getColor(R.color.navigation_app)));
        this.getSupportActionBar().setCustomView(R.layout.toolbar_title_search_movie);

        this.getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_navigate_before_24);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initSearchMovieRecyclerView();

    }

    private void initSearchMovieRecyclerView() {
        this.recyclerViewSearchMovie = findViewById(R.id.recycler_view_search_movie);
        this.recyclerViewSearchMovie.setLayoutManager(new LinearLayoutManager(this));
    }

    private void searchMovies(){
        String title = this.editTextTitleMovie.getText().toString();
        MovieSearchRepository repository =
                new MovieSearchRepository("https://api.themoviedb.org/3/search/movie?api_key=57a5540deefb424e87c1582ddecaae46&language=ru-RU&query="+title);
        this.results = repository.getResultList();
        this.searchAdapter = new MovieSearchAdapter(results, this);
        this.recyclerViewSearchMovie.setAdapter(searchAdapter);

        this.searchAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;

            case R.id.delete_text:
                this.editTextTitleMovie.setText("");
                break;
        }
        return true;
    }

}