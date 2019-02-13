package com.example.moviematerialdesignmaster.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.moviematerialdesignmaster.R;

import me.zhanghai.android.materialratingbar.MaterialRatingBar;

public class MovieViewHolder extends RecyclerView.ViewHolder {
    public final ImageView imageThumbnail;
    public final MaterialRatingBar ratingBar;
    public final TextView titleMovie;
    public final TextView txtRating;
    public MovieViewHolder(@NonNull View itemView) {
        super(itemView);
        imageThumbnail = itemView.findViewById(R.id.imageThumbnail);
        ratingBar = itemView.findViewById(R.id.rating);
        titleMovie = itemView.findViewById(R.id.txtTitleMovie);
        txtRating = itemView.findViewById(R.id.txtRating);

    }

}
