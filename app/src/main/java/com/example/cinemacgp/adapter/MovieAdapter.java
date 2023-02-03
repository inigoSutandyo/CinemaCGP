package com.example.cinemacgp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cinemacgp.R;
import com.example.cinemacgp.interfaces.IRecyclerView;
import com.example.cinemacgp.model.Movie;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    private ArrayList<Movie> movies;
    private IRecyclerView recyclerViewInterface;

    public MovieAdapter(ArrayList<Movie> movies, IRecyclerView recyclerViewInterface) {
        this.movies = movies;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_movie, parent, false);
        return new MovieAdapter.ViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.getTitleView().setText(movie.getTitle());
        holder.getScoreView().setText(movie.getScore().toString());
        holder.getRatingView().setText(movie.getRating());
        holder.getYearView().setText(movie.getYear().toString());
        Glide.with(holder.itemView)
                .load(movie.getImage())
                .into(holder.getImageView());
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView titleView, ratingView, scoreView, yearView;
        private ImageView imageView;

        public ViewHolder(@NonNull View itemView, IRecyclerView recyclerViewInterface) {
            super(itemView);
            titleView = itemView.findViewById(R.id.movie_title);
            ratingView = itemView.findViewById(R.id.movie_rating);
            scoreView = itemView.findViewById(R.id.movie_score);
            imageView = itemView.findViewById(R.id.movie_image);
            yearView = itemView.findViewById(R.id.movie_year);
        }

        public TextView getYearView() {
            return yearView;
        }

        public TextView getTitleView() {
            return titleView;
        }

        public TextView getRatingView() {
            return ratingView;
        }

        public TextView getScoreView() {
            return scoreView;
        }

        public ImageView getImageView() {
            return imageView;
        }
    }
}
