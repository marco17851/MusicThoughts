package com.marcobarragan.thoughtmusic.artist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.marcobarragan.thoughtmusic.R;
import com.marcobarragan.thoughtmusic.genre.GenreAdapter;
import com.marcobarragan.thoughtmusic.models.Artist;

import java.util.List;

import javax.inject.Inject;

import static dagger.internal.Preconditions.checkNotNull;

public class ArtistFragment extends Fragment implements ArtistContract.View, ArtistAdapter.ArtistAdapterOnClickHandler {

    private RecyclerView mRecyclerView;
    private TextView mErrorView;

    @Inject
    ArtistAdapter mArtistAdapter;

    @Inject
    ArtistPresenter mPresenter;

    public static ArtistFragment newInstance() {
        ArtistFragment fragment = new ArtistFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_artists, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.artists_recycler_view);
        mErrorView = (TextView) view.findViewById(R.id.artists_error_message);
        hideErrors();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mArtistAdapter);

        return view;
    }

    @Override
    public void setPresenter(ArtistContract.Presenter presenter) {
        mPresenter = (ArtistPresenter) checkNotNull(presenter);
    }

    @Override
    public void setArtists(List<Artist> artists) {
        if (mArtistAdapter.setArtists(artists)){
            hideErrors();
        } else {
            showErrorMessage();
        }
    }

    private void hideErrors() {
        mErrorView.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showErrorMessage() {
        mErrorView.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void onClick(List<Integer> songIds) {

    }
}
