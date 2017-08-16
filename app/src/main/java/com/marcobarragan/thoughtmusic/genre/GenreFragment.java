package com.marcobarragan.thoughtmusic.genre;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.marcobarragan.thoughtmusic.R;
import com.marcobarragan.thoughtmusic.models.Genre;

import java.util.List;

public class GenreFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private GenreAdapter mGenreAdapter;

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

    public int getGenresSize() {
        return mGenreAdapter.getItemCount();
    }
}
