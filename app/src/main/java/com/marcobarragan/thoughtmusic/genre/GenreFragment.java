package com.marcobarragan.thoughtmusic.genre;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.marcobarragan.thoughtmusic.R;
import com.marcobarragan.thoughtmusic.models.Genre;
import com.marcobarragan.thoughtmusic.songs.SongsActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static dagger.internal.Preconditions.checkNotNull;

public class GenreFragment extends Fragment implements GenreContract.View, GenreAdapter.GenreAdapterOnClickHandler{

    private RecyclerView mRecyclerView;
    private TextView mErrorView;

    @Inject
    GenreAdapter mGenreAdapter;

    @Inject
    GenrePresenter mPresenter;

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
        mErrorView = (TextView) view.findViewById(R.id.error_message);
        hideErrors();
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mRecyclerView.setAdapter(mGenreAdapter);

        return view;
    }

    private void hideErrors() {
        mErrorView.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setGenres(List<Genre> genres) {
        if (mGenreAdapter.setGenres(genres)){
            hideErrors();
        } else {
            showErrorMessage();
        }

    }

    @Override
    public void showErrorMessage() {
        mErrorView.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void setPresenter(GenreContract.Presenter presenter) {
        mPresenter = (GenrePresenter) checkNotNull(presenter);
    }

    @Override
    public void onClick(List<Integer> songIds) {
        Bundle bundle = new Bundle();
        bundle.putIntegerArrayList("song_ids", (ArrayList<Integer>) songIds);

        Intent intent = new Intent(getActivity(), SongsActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
