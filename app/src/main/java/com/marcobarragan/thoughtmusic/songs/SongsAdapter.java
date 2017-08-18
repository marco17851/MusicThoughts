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

class SongsAdapter extends RecyclerView.Adapter<SongsAdapter.SongsAdapterViewHolder> {

    private Context mContext;

    public SongsAdapter(@NonNull @ActivityContext Context context) {
        mContext = context;
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
            holder.titleView.setText("Mambo No. 5");
            holder.typeView.setText("Basic");
            holder.descriptionView.setText("Hit song from 1999!");
        }
    }

    @Override
    public int getItemCount() {
        return 1;
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
