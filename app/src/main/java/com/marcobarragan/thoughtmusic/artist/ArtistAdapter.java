package com.marcobarragan.thoughtmusic.artist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.marcobarragan.thoughtmusic.R;
import com.marcobarragan.thoughtmusic.dagger.ActivityContext;
import com.marcobarragan.thoughtmusic.models.Artist;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.ArtistAdapterViewHolder>{
    private Context mContext;
    private List<Artist> mArtists;
    private ArtistAdapterOnClickHandler mClickHandler;

    @Inject
    public ArtistAdapter(@NonNull @ActivityContext Context context, ArtistAdapterOnClickHandler clickHandler) {
        mContext = context;
        mArtists = new ArrayList<>();
        mClickHandler = clickHandler;
    }

    public boolean setArtists(List<Artist> artists) {
        if (artists != null && artists.size() > 0) {
            mArtists = artists;
            notifyDataSetChanged();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public ArtistAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId = R.layout.category_list_item;

        View view = LayoutInflater.from(mContext).inflate(layoutId, parent, false);

        view.setFocusable(true);

        return new ArtistAdapter.ArtistAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ArtistAdapterViewHolder holder, int position) {
        if (getItemCount() > position) {
            Artist artist = mArtists.get(position);
            Picasso.with(mContext).load(artist.getCover()).placeholder(R.drawable.broken_image).fit().into(holder.coverView);
            holder.titleView.setText(mArtists.get(position).getCategory());
        }
    }

    @Override
    public int getItemCount() {
        return mArtists.size();
    }

    public class ArtistAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView coverView;
        private TextView titleView;

        ArtistAdapterViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            titleView = (TextView) view.findViewById(R.id.item_list_title);
            coverView = (ImageView) view.findViewById(R.id.item_list_cover);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            List<Integer> songIds = mArtists.get(position).getSongIds();
            mClickHandler.onClick(songIds);
        }
    }

    public interface ArtistAdapterOnClickHandler{
        void onClick(List<Integer> songIds);
    }
}
