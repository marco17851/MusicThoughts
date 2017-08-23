package com.marcobarragan.thoughtmusic.album;

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
import com.marcobarragan.thoughtmusic.models.Album;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumAdapterViewHolder>{
    private Context mContext;
    private List<Album> mAlbums;
    private AlbumAdapterOnClickHandler mClickHandler;

    @Inject
    public AlbumAdapter(@NonNull @ActivityContext Context context, AlbumAdapterOnClickHandler clickHandler) {
        mContext = context;
        mAlbums = new ArrayList<>();
        mClickHandler = clickHandler;
    }

    public boolean setAlbums(List<Album> albums) {
        if (albums != null && albums.size() > 0) {
            mAlbums = albums;
            notifyDataSetChanged();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public AlbumAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId = R.layout.category_list_item;

        View view = LayoutInflater.from(mContext).inflate(layoutId, parent, false);

        view.setFocusable(true);

        return new AlbumAdapter.AlbumAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AlbumAdapterViewHolder holder, int position) {
        if (getItemCount() > position) {
            Album artist = mAlbums.get(position);
            Picasso.with(mContext).load(artist.getCover()).placeholder(R.drawable.broken_image).fit().into(holder.coverView);
            holder.titleView.setText(mAlbums.get(position).getCategory());
        }
    }

    @Override
    public int getItemCount() {
        return mAlbums.size();
    }

    public class AlbumAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView coverView;
        private TextView titleView;

        AlbumAdapterViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            titleView = (TextView) view.findViewById(R.id.item_list_title);
            coverView = (ImageView) view.findViewById(R.id.item_list_cover);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            List<Integer> songIds = mAlbums.get(position).getSongIds();
            mClickHandler.onClick(songIds);
        }
    }

    public interface AlbumAdapterOnClickHandler{
        void onClick(List<Integer> songIds);
    }
}
