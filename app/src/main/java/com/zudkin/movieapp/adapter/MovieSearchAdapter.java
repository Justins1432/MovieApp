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
import com.zudkin.movieapp.activities.SearchInfoMovie;
import com.zudkin.movieapp.model.Result;

import java.util.List;

public class MovieSearchAdapter extends RecyclerView.Adapter<MovieSearchAdapter.MovieSearchViewHolder> {
    private List<Result> results;
    private Context context;

    public MovieSearchAdapter(List<Result> results, Context context) {
        this.results = results;
        this.context = context;
    }

    @NonNull
    @Override
    public MovieSearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.search_movie_recycler_view_item, parent, false);
        return new MovieSearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieSearchViewHolder holder, int position) {
        Result result = this.results.get(position);

        holder.linearLayout.setOnClickListener(v -> {
            Context context = holder.linearLayout.getContext();
            Intent intent = new Intent(context, SearchInfoMovie.class);
            intent.putExtra(SearchInfoMovie.SEARCH_INFO_MOVIE, result);
            context.startActivity(intent);
        });

        holder.title.setText(result.getTitle());

        Glide.with(this.context)
                .load("https://image.tmdb.org/t/p/original" +
                        this.results.get(position).getPosterPath())
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        if (this.results == null){
            return 0;
        }
        return this.results.size();
    }

    public class MovieSearchViewHolder extends RecyclerView.ViewHolder {
        LinearLayout linearLayout;
        ImageView imageView;
        TextView title;
        public MovieSearchViewHolder(@NonNull View itemView) {
            super(itemView);
            this.linearLayout = itemView.findViewById(R.id.searchMovieForeground);
            this.imageView = itemView.findViewById(R.id.search_poster_movie);
            this.title = itemView.findViewById(R.id.search_title_movie);
        }
    }

}
