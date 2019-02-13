package com.example.moviematerialdesignmaster.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.moviematerialdesignmaster.Common.Common;
import com.example.moviematerialdesignmaster.Models.Movie;
import com.example.moviematerialdesignmaster.R;
import com.example.moviematerialdesignmaster.ViewHolder.MovieViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieViewHolder> {
    private final List<Movie> lists;
    private final Context context;

    public MovieAdapter(List<Movie> lists, Context context) {
        this.lists = lists;
        this.context = context;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_movies, viewGroup, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, int i) {
        Picasso.with(context).load(Common.BACKDROP_URL + lists.get(i).getPoster_path()).into(movieViewHolder.imageThumbnail);
        movieViewHolder.titleMovie.setText(lists.get(i).getTitle());
        movieViewHolder.txtRating.setText(String.valueOf(lists.get(i).getVote_average()));
        movieViewHolder.ratingBar.setRating(Float.parseFloat(lists.get(i).getVote_average() / 2 + ""));
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }
}
