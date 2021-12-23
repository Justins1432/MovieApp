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
import com.zudkin.movieapp.activities.FavouritesInfo;
import com.zudkin.movieapp.activities.FavouritesMovie;
import com.zudkin.movieapp.download.DownLoadPosters;
import com.zudkin.movieapp.model.Result;

import java.util.List;

public class FavouritesAdapter extends RecyclerView.Adapter<FavouritesAdapter.FavouritesViewHolder> {
    private List<Result> results;
    private Context context;

    public FavouritesAdapter(List<Result> results, Context context) {
        this.results = results;
        this.context = context;
    }

    @NonNull
    @Override
    public FavouritesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.favourites_recycler_view_item, parent, false);
        return new FavouritesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavouritesViewHolder holder, int position) {
        Result result = this.results.get(position);

        holder.linearLayout.setOnClickListener(v -> {
            Context context = holder.linearLayout.getContext();
            long id = result.getId();
            Intent intent = new Intent(context, FavouritesMovie.class);
            intent.putExtra(FavouritesMovie.MOVIE_FAVOURITES, result);
            intent.putExtra("id", id);
            context.startActivity(intent);
        });

        holder.linearLayout.setOnClickListener(v -> {
            Context context = holder.linearLayout.getContext();
            long l = result.getId();
            Intent intent = new Intent(context, FavouritesInfo.class);
            intent.putExtra(FavouritesInfo.FAVOURITE_INFO, result);
            intent.putExtra("idFavourite", l);
            context.startActivity(intent);
        });

        holder.title.setText(result.getTitle());

        DownLoadPosters posters = new DownLoadPosters(holder.imageView, 160, 110);
        posters.execute("https://image.tmdb.org/t/p/original" + this.results.get(position).getPosterPath());

    }

    @Override
    public int getItemCount() {
        return this.results.size();
    }

    public class FavouritesViewHolder extends RecyclerView.ViewHolder {
        LinearLayout linearLayout;
        ImageView imageView;
        TextView title;
        public FavouritesViewHolder(@NonNull View itemView) {
            super(itemView);
            this.linearLayout = itemView.findViewById(R.id.favouritesForeground);
            this.imageView = itemView.findViewById(R.id.poster_favourites);
            this.title = itemView.findViewById(R.id.title_favourites);
        }
    }
}
