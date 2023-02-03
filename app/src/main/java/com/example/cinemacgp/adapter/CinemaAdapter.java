package com.example.cinemacgp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinemacgp.R;
import com.example.cinemacgp.interfaces.IRecyclerView;
import com.example.cinemacgp.model.Cinema;

import java.util.ArrayList;

public class CinemaAdapter extends RecyclerView.Adapter<CinemaAdapter.ViewHolder> {

    private ArrayList<Cinema> cinemas;
    private IRecyclerView recyclerViewInterface;

    public CinemaAdapter(ArrayList<Cinema> cinemas, IRecyclerView recyclerViewInterface) {
        this.cinemas = cinemas;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cinema, parent, false);
        return new CinemaAdapter.ViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return cinemas.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nameView, addressView;
        private Button bookingButton, mapButton;
        public ViewHolder(@NonNull View itemView, IRecyclerView recyclerViewInterface) {
            super(itemView);
            nameView = itemView.findViewById(R.id.cinema_name);
            addressView = itemView.findViewById(R.id.cinema_address);
            bookingButton = itemView.findViewById(R.id.button_book);
            mapButton = itemView.findViewById(R.id.button_location);
            bookingButton.setOnClickListener(view -> {
                int pos = getAdapterPosition();
                if (pos != RecyclerView.NO_POSITION) {
                    recyclerViewInterface.onItemClick(pos, "book", "cinema");
                }
            });

            mapButton.setOnClickListener(view -> {
                int pos = getAdapterPosition();
                if (pos != RecyclerView.NO_POSITION) {
                    recyclerViewInterface.onItemClick(pos, "map", "cinema");
                }
            });
        }


    }
}
