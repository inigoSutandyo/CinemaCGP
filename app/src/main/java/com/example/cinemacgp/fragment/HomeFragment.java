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
import com.example.cinemacgp.adapter.MovieAdapter;
import com.example.cinemacgp.controller.MovieController;
import com.example.cinemacgp.interfaces.IListener;
import com.example.cinemacgp.interfaces.IRecyclerView;
import com.example.cinemacgp.model.Movie;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements IRecyclerView, IListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ArrayList<Movie> movies;
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
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
        page = 1;
        loading = false;
        movies = new ArrayList<>();
        MovieController.fetchTop(this.getActivity(), this, page);
        movieAdapter = new MovieAdapter(movies, this);
        recyclerView = view.findViewById(R.id.movie_recycler);
        recyclerView.setAdapter(movieAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getActivity());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!loading && page <= 2) {
                    LinearLayoutManager layoutManager=LinearLayoutManager.class.cast(recyclerView.getLayoutManager());
                    int lastVisible = layoutManager.findLastCompletelyVisibleItemPosition();
                    if (linearLayoutManager != null && lastVisible == movieAdapter.getItemCount() - 1) {
                        loadMore();
                        loading = true;
                    }
                }
            }
        });
    }

    private void loadMore() {
        page += 1;
        MovieController.fetchTop(this.getActivity(), this, page);
    }

    @Override
    public void onItemClick(int position, String action) {

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
}