package com.marcobarragan.thoughtmusic.genre;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.marcobarragan.thoughtmusic.R;
import com.marcobarragan.thoughtmusic.app.ThoughtMusicApplication;
import com.marcobarragan.thoughtmusic.main.MainModule;
import com.marcobarragan.thoughtmusic.models.Genre;

import java.util.List;

import javax.inject.Inject;

public class GenreFragment extends Fragment {

    private RecyclerView mRecyclerView;

    LinearLayoutManager mLayoutManager;

    private GenreAdapter mGenreAdapter;

    public static GenreFragment newInstance() {
        GenreFragment fragment = new GenreFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_genres, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.genres_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mGenreAdapter = new GenreAdapter(getActivity());
        mRecyclerView.setAdapter(mGenreAdapter);

        return view;
    }


    public void setGenres(List<Genre> genres) {
        mGenreAdapter.setGenres(genres);
    }
}
