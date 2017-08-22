package com.marcobarragan.thoughtmusic.album;

import android.content.Intent;
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
import com.marcobarragan.thoughtmusic.models.Album;
import com.marcobarragan.thoughtmusic.songs.SongsActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static dagger.internal.Preconditions.checkNotNull;

public class AlbumFragment extends Fragment implements AlbumContract.View, AlbumAdapter.AlbumAdapterOnClickHandler {

    private RecyclerView mRecyclerView;
    private TextView mErrorView;

    @Inject
    AlbumAdapter mAlbumAdapter;

    @Inject
    AlbumPresenter mPresenter;

    public static AlbumFragment newInstance() {
        AlbumFragment fragment = new AlbumFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_albums, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.albums_recycler_view);
        mErrorView = (TextView) view.findViewById(R.id.albums_error_message);
        hideErrors();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mAlbumAdapter);

        return view;
    }

    @Override
    public void setPresenter(AlbumContract.Presenter presenter) {
        mPresenter = (AlbumPresenter) checkNotNull(presenter);
    }

    @Override
    public void setAlbums(List<Album> albums) {
        if (mAlbumAdapter.setAlbums(albums)){
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
        Bundle bundle = new Bundle();
        bundle.putIntegerArrayList("song_ids", (ArrayList<Integer>) songIds);

        Intent intent = new Intent(getActivity(), SongsActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
