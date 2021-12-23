package com.zudkin.movieapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.zudkin.movieapp.R;
import com.zudkin.movieapp.activities.DetailMovie;
import com.zudkin.movieapp.model.Result;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private List<Result> results;
    private Context context;

    public MovieAdapter(Context context, List<Result> results){
        this.context = context;
        this.results = results;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.movie_recycler_view_item, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Result result = this.results.get(position);

        holder.linearLayout.setOnClickListener(view -> {
            long resultId = result.getId();
            Context context = holder.linearLayout.getContext();
            Intent intent = new Intent(context, DetailMovie.class);
            intent.putExtra("resultId", resultId);
            intent.putExtra(DetailMovie.MOVIE, result);
            context.startActivity(intent);
        });

        holder.title.setText(result.getTitle());
        holder.release.setText(result.getReleaseDate());
        holder.average.setText(String.valueOf(result.getVoteAverage()));

        Glide.with(this.context)
                .load("https://image.tmdb.org/t/p/original" +
                        this.results.get(position).getPosterPath())
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return this.results.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        LinearLayout linearLayout;
        ImageView imageView;
        TextView title, release, average, genre;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            this.linearLayout = itemView.findViewById(R.id.movieForeground);
            this.imageView = itemView.findViewById(R.id.poster_movie);
            this.title = itemView.findViewById(R.id.title_movie);
            this.release = itemView.findViewById(R.id.release_movie);
            this.average = itemView.findViewById(R.id.voteAverage_movie);


        }

    }

}
