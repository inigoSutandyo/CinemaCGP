package com.example.cinemacgp.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.cinemacgp.R;
import com.example.cinemacgp.controller.CinemaController;
import com.example.cinemacgp.controller.MovieController;
import com.example.cinemacgp.interfaces.IListener;
import com.example.cinemacgp.model.Cinema;
import com.example.cinemacgp.model.Movie;
import com.example.cinemacgp.model.Theater;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddBookingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddBookingFragment extends Fragment implements IListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Spinner movieSpinner, theaterSpinner;
    private EditText nameTxt, emailTxt;
    private TextView cinemaTxt, errorTxt;
    private Cinema cinema;
    private ArrayList<Movie> movies;
    private int page;
    public AddBookingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddBookingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddBookingFragment newInstance(String param1, String param2) {
        AddBookingFragment fragment = new AddBookingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_booking, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        movies = new ArrayList<>();
        page = 1;

        Bundle bundle = getArguments();
        int id = bundle.getInt("CINEMA_ID");
        cinema = CinemaController.getCinemaById(id);
        cinemaTxt = view.findViewById(R.id.booking_cinema_name);
        cinemaTxt.setText(cinema.getName());

        errorTxt = view.findViewById(R.id.error_txt);

        movieSpinner = view.findViewById(R.id.movie_spinner);
        theaterSpinner = view.findViewById(R.id.theater_spinner);
        populateTheaterSpinner();

        nameTxt = view.findViewById(R.id.txt_name);
        emailTxt = view.findViewById(R.id.txt_email);

        Button book = view.findViewById(R.id.add_booking_btn);

        book.setOnClickListener(view1 -> {
            errorTxt.setVisibility(View.INVISIBLE);
            String name = nameTxt.getText().toString();
            String email = emailTxt.getText().toString();
            if (email.isEmpty() || name.isEmpty()) {
                errorTxt.setText("Fields must be filled!!");
                errorTxt.setVisibility(View.VISIBLE);
            }
            if (movieSpinner.getSelectedItem() == null || theaterSpinner.getSelectedItem() == null) {
                errorTxt.setText("Spinner error");
                errorTxt.setVisibility(View.VISIBLE);
            }

            Movie movie = (Movie) movieSpinner.getSelectedItem();
            Theater theater = (Theater) theaterSpinner.getSelectedItem();
            CinemaController.addBooking(name, email, cinema, movie, theater);
        });

        MovieController.fetchTop(this.getActivity(), this, page++);

    }

    private void populateMovieSpinner() {
        ArrayAdapter<Movie> adapter = new ArrayAdapter<Movie>(this.getActivity(), android.R.layout.simple_spinner_dropdown_item, movies);
        movieSpinner.setAdapter(adapter);
    }

    private void populateTheaterSpinner() {
        ArrayAdapter<Theater> adapter = new ArrayAdapter<Theater>(this.getActivity(), android.R.layout.simple_spinner_item, cinema.getTheaters());
        theaterSpinner.setAdapter(adapter);
    }

    @Override
    public void onSuccess(Movie... movies) {
        this.movies = MovieController.getDatabaseMovies();
        if (this.movies.size() >= 50 || page > 2) {
            populateMovieSpinner();
            return;
        }
        MovieController.addAllMovies(movies);
        MovieController.fetchTop(this.getActivity(), this, page++);
    }
}