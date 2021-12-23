package com.zudkin.movieapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.zudkin.movieapp.model.Result;

import java.util.ArrayList;
import java.util.List;

public class MovieDatabaseHandler extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "DatabaseMovie";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_MOVIES = "movies";
    public static final String KEY_MOVIE_ID = "id";
    public static final String KEY_MOVIE_TITLE = "title";
    public static final String KEY_MOVIE_RELEASE = "release_movie";
    public static final String KEY_MOVIE_VOTE_AVERAGE = "vote_average";
    public static final String KEY_MOVIE_OVERVIEW = "overview";
    public static final String KEY_MOVIE_POSTER = "poster";


    public MovieDatabaseHandler(@Nullable Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlMovie = "create table " + TABLE_MOVIES + "(" +
                KEY_MOVIE_ID + " integer primary key," +
                KEY_MOVIE_TITLE + " varchar(100) not null," +
                KEY_MOVIE_RELEASE + " varchar(10) not null," +
                KEY_MOVIE_VOTE_AVERAGE + " double(5) not null," +
                KEY_MOVIE_OVERVIEW + " text not null," +
                KEY_MOVIE_POSTER + " varchar(100) not null)";
        db.execSQL(sqlMovie);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        db.execSQL("pragma foreign_keys = ON");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    public boolean addMovie(Result result){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_MOVIE_TITLE, result.getTitle());
        contentValues.put(KEY_MOVIE_RELEASE, result.getReleaseDate());
        contentValues.put(KEY_MOVIE_VOTE_AVERAGE, result.getVoteAverage());
        contentValues.put(KEY_MOVIE_OVERVIEW, result.getOverview());
        contentValues.put(KEY_MOVIE_POSTER, result.getPosterPath());
        long id = database.insert(TABLE_MOVIES, null, contentValues);
        result.setId(id);
        return id > 0;
    }

    public List<Result> getResults(){
        List<Result> results = new ArrayList<>();
        String sql = "select * from " + TABLE_MOVIES;

        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(sql, null);

        if (cursor.moveToFirst()){
            do {
                Result result = new Result();
                result.setId(cursor.getLong(0));
                result.setTitle(cursor.getString(1));
                result.setReleaseDate(cursor.getString(2));
                result.setVoteAverage(cursor.getDouble(3));
                result.setOverview(cursor.getString(4));
                result.setPosterPath(cursor.getString(5));
                results.add(result);
            }while (cursor.moveToNext());
        }

        cursor.close();
        database.close();
        return results;
    }

    public void deleteMovie(long idMovie){
        String sql = "delete from " + TABLE_MOVIES + " where id=" + idMovie;
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL(sql);
        database.close();
    }

}
