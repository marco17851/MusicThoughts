package com.marcobarragan.thoughtmusic.genre;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.marcobarragan.thoughtmusic.R;
import com.marcobarragan.thoughtmusic.dagger.ActivityContext;
import com.marcobarragan.thoughtmusic.models.Genre;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.GenreAdapterViewHolder>{
    private List<Genre> mGenres;
    private Context mContext;

    private GenreAdapterOnClickHandler mClickHandler;

    @Inject
    public GenreAdapter(@NonNull @ActivityContext Context context, GenreAdapterOnClickHandler clickHandler) {
        mContext = context;
        mGenres = new ArrayList<>();
        mClickHandler = clickHandler;
    }

    @Override
    public GenreAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId = R.layout.genre_list_item;

        View view = LayoutInflater.from(mContext).inflate(layoutId, parent, false);

        view.setFocusable(true);

        return new GenreAdapter.GenreAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GenreAdapterViewHolder holder, int position) {
        if (getItemCount() > position) {
            holder.titleView.setText(mGenres.get(position).getCategory());
        }
    }

    @Override
    public int getItemCount() {
        return mGenres.size();
    }

    public boolean setGenres(List<Genre> genres){
        if (genres != null && genres.size() > 0) {
            mGenres = genres;
            notifyDataSetChanged();
            return true;
        } else {
            return false;
        }
    }

    /**
     * A ViewHolder is a required part of the pattern for RecyclerViews. It mostly behaves as
     * a cache of the child views for a forecast item. It's also a convenient place to set an
     * OnClickListener, since it has access to the adapter and the views.
     */
    public class GenreAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView titleView;

        GenreAdapterViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            titleView = (TextView) view.findViewById(R.id.genre_list_title);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            List<Integer> songIds = mGenres.get(position).getSongIds();
            mClickHandler.onClick(songIds);
        }
    }

    public interface GenreAdapterOnClickHandler{
        void onClick(List<Integer> songIds);
    }
}
