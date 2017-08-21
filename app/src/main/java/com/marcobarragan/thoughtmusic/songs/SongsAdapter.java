package com.marcobarragan.thoughtmusic.songs;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.marcobarragan.thoughtmusic.R;
import com.marcobarragan.thoughtmusic.dagger.ActivityContext;
import com.marcobarragan.thoughtmusic.models.Song;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

class SongsAdapter extends RecyclerView.Adapter<SongsAdapter.SongsAdapterViewHolder> {

    private List<Song> mSongs;
    private Context mContext;

    @Inject
    public SongsAdapter(@NonNull @ActivityContext Context context) {
        mContext = context;
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

    public class SongsAdapterViewHolder extends RecyclerView.ViewHolder {

        private TextView titleView;
        private TextView typeView;
        private TextView descriptionView;

        SongsAdapterViewHolder(View view) {
            super(view);
            titleView = (TextView) view.findViewById(R.id.song_title);
            typeView = (TextView) view.findViewById(R.id.song_type);
            descriptionView = (TextView) view.findViewById(R.id.song_description);
        }
    }
}
