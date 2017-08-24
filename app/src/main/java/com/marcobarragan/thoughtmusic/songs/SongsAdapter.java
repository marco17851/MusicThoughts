package com.marcobarragan.thoughtmusic.songs;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.marcobarragan.thoughtmusic.R;
import com.marcobarragan.thoughtmusic.dagger.ActivityContext;
import com.marcobarragan.thoughtmusic.models.Song;
import com.marcobarragan.thoughtmusic.network.ImageDownloader;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class SongsAdapter extends RecyclerView.Adapter<SongsAdapter.SongsAdapterViewHolder> {

    private List<Song> mSongs;
    private Context mContext;
    private SongsAdapterOnClickHandler mOnClickHandler;
    private ImageDownloader mImageDownloader;

    @Inject
    public SongsAdapter(@NonNull @ActivityContext Context context, SongsAdapterOnClickHandler onClickHandler, ImageDownloader imageDownloader) {
        mContext = context;
        mOnClickHandler = onClickHandler;
        mImageDownloader = imageDownloader;
        mSongs = new ArrayList<>();
    }

    @Override
    public SongsAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId = R.layout.song_list_item;

        View view = LayoutInflater.from(mContext).inflate(layoutId, parent, false);

        view.setFocusable(true);

        return new SongsAdapter.SongsAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SongsAdapterViewHolder holder, int position) {
        if (getItemCount() > position) {
            Song song = mSongs.get(position);
            mImageDownloader.loadImageFromUrl(song.getCover(), holder.coverImageView);

            holder.titleView.setText(song.getTitle());
            holder.typeView.setText(song.getType());
            holder.descriptionView.setText(song.getDescription());
        }
    }

    @Override
    public int getItemCount() {
        return mSongs.size();
    }

    public boolean setSongs(List<Song> songs) {
        if (songs != null && songs.size() > 0) {
            mSongs = songs;
            notifyDataSetChanged();
            return true;
        } else {
            return false;
        }
    }

    public class SongsAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView coverImageView;
        private TextView titleView;
        private TextView typeView;
        private TextView descriptionView;

        SongsAdapterViewHolder(View view) {
            super(view);
            coverImageView = (ImageView) view.findViewById(R.id.song_cover);
            titleView = (TextView) view.findViewById(R.id.song_title);
            typeView = (TextView) view.findViewById(R.id.song_type);
            descriptionView = (TextView) view.findViewById(R.id.song_description);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Song song = mSongs.get(position);

            View navBar = ((Activity) mContext).findViewById(android.R.id.navigationBarBackground);
            Pair<View, String> navBarPair = Pair.create(navBar, Window.NAVIGATION_BAR_BACKGROUND_TRANSITION_NAME);
            Pair<View, String> coverPair = Pair.create((View) coverImageView, mContext.getString(R.string.cover_transition));

            ActivityOptionsCompat options = ActivityOptionsCompat.
                    makeSceneTransitionAnimation((Activity) mContext, coverPair, navBarPair);

            mOnClickHandler.onClick(song, options);
        }
    }

    public interface SongsAdapterOnClickHandler {
        void onClick(Song song, ActivityOptionsCompat optionsCompat);
    }
}
