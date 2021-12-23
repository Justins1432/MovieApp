package com.zudkin.movieapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;

import com.zudkin.movieapp.R;
import com.zudkin.movieapp.main.MainActivity;

public class AppInformation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_infrormation);
        initComponents();
    }

    private void initComponents(){
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        this.getSupportActionBar().setCustomView(R.layout.toolbar_layout_app);
        this.getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().
                getColor(R.color.navigation_app)));
        this.getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_navigate_before_24);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                Intent intent = new Intent(AppInformation.this, MainActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}