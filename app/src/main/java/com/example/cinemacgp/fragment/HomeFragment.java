package com.example.cinemacgp.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cinemacgp.R;
import com.example.cinemacgp.adapter.CinemaAdapter;
import com.example.cinemacgp.adapter.MovieAdapter;
import com.example.cinemacgp.controller.MovieController;
import com.example.cinemacgp.db.Database;
import com.example.cinemacgp.interfaces.IFragment;
import com.example.cinemacgp.interfaces.IListener;
import com.example.cinemacgp.interfaces.IRecyclerView;
import com.example.cinemacgp.model.Cinema;
import com.example.cinemacgp.model.Movie;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements IRecyclerView, IListener, IFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Database database;
    private ArrayList<Movie> movies;
    private RecyclerView movieRecycler;
    private MovieAdapter movieAdapter;

    private ArrayList<Cinema> cinemas;
    private RecyclerView cinemaRecycler;
    private CinemaAdapter cinemaAdapter;

    private int page;
    private boolean loading;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        database = Database.getInstance();
        initCinemaList(view);
        initMovieList(view);
        recyclerViewListener();
    }

    private void initCinemaList(View view) {
        cinemaRecycler = view.findViewById(R.id.cinema_recycler);
        cinemas = new ArrayList<>();
        cinemaAdapter = new CinemaAdapter(cinemas, this);
        cinemas.addAll(database.getCinemas());
        cinemaRecycler.setAdapter(cinemaAdapter);
        cinemaRecycler.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        cinemaAdapter.notifyDataSetChanged();
    }

    private void recyclerViewListener() {
        movieRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!loading && page <= 2) {
                    LinearLayoutManager layoutManager = LinearLayoutManager.class.cast(recyclerView.getLayoutManager());
                    int lastVisible = layoutManager.findLastCompletelyVisibleItemPosition();
                    if (layoutManager != null && lastVisible == movieAdapter.getItemCount() - 1) {
                        loadMoreMovie();
                        loading = true;
                    }
                }
            }
        });
    }

    private void initMovieList(View view) {
        page = 1;
        loading = false;
        movies = new ArrayList<>();
        MovieController.fetchTop(this.getActivity(), this, page);
        movieAdapter = new MovieAdapter(movies, this);
        movieRecycler = view.findViewById(R.id.movie_recycler);
        movieRecycler.setAdapter(movieAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getActivity());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        movieRecycler.setLayoutManager(linearLayoutManager);
    }

    private void loadMoreMovie() {
        page += 1;
        MovieController.fetchTop(this.getActivity(), this, page);
    }

    @Override
    public void onItemClick(int position, String action, String source) {
        if (source.equals("cinema")) {
            if (action.equals("book")) {
                // Do something

            }

            if (action.equals("map")) {
                // DO SOMETHING
            }
        }
    }



    @Override
    public void onSuccess(Movie... movies) {
        if (movies.length < 1) {
            return;
        }
        for (Movie m :
                movies) {
            this.movies.add(m);
        }
        loading = false;
        movieAdapter.notifyDataSetChanged();

        Log.d("SCROLL", "movie count: " + movieAdapter.getItemCount() + " page: " + page);
    }

    @Override
    public void replaceFragment(Fragment fragment) {

    }
}