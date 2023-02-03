package com.example.cinemacgp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinemacgp.R;
import com.example.cinemacgp.model.Booking;

import java.util.ArrayList;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.ViewHolder> {

    private ArrayList<Booking> bookings;

    public BookingAdapter(ArrayList<Booking> bookings) {
        this.bookings = bookings;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_booking, parent, false);
        return new BookingAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Booking booking = bookings.get(position);

        holder.detailView.setText(booking.getCinema().getName() + " - " + booking.getMovie().getTitle());
        holder.emailView.setText(booking.getEmail());
        holder.nameView.setText(booking.getName());
        holder.theaterView.setText(booking.getTheater().getNumber() + "");
    }

    @Override
    public int getItemCount() {
        return bookings.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView detailView, theaterView, nameView, emailView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            detailView = itemView.findViewById(R.id.book_detail);
            theaterView = itemView.findViewById(R.id.book_theater);
            nameView = itemView.findViewById(R.id.book_name);
            emailView = itemView.findViewById(R.id.book_email);
        }
    }
}
