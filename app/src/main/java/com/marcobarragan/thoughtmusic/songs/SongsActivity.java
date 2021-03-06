package com.marcobarragan.thoughtmusic.songs;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.NotificationCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.TextView;

import com.marcobarragan.thoughtmusic.R;
import com.marcobarragan.thoughtmusic.models.Song;
import com.marcobarragan.thoughtmusic.musicPlayer.MusicPlayerActivity;
import com.marcobarragan.thoughtmusic.network.NetModule;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static dagger.internal.Preconditions.checkNotNull;

public class SongsActivity extends AppCompatActivity implements SongsContract.View, SongsAdapter.SongsAdapterOnClickHandler {

    private RecyclerView mRecyclerView;

    private TextView mErrorView;

    @Inject
    SongsAdapter mSongsAdapter;

    @Inject
    SongsPresenter mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        getSongComponent().inject(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songs);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle extras = getIntent().getExtras();
        ArrayList<Integer> song_ids = (ArrayList<Integer>) extras.get("song_ids");

        mPresenter.setSongIds(song_ids);

        mErrorView = (TextView) findViewById(R.id.error_message);

        mRecyclerView = (RecyclerView) findViewById(R.id.songs_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mSongsAdapter);
    }

    private SongsComponent getSongComponent() {
        return DaggerSongsComponent.builder()
                .songsModule(new SongsModule(this))
                .netModule(new NetModule("http://10.0.2.2:3000/"))
                .build();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresenter(SongsContract.Presenter presenter) {
        mPresenter = (SongsPresenter) checkNotNull(presenter);
    }

    @Override
    public void setSongs(List<Song> songs) {
        if (mSongsAdapter.setSongs(songs)){
            hideErrors();
        } else {
            showErrorMessage();
        }
    }

    private void hideErrors() {
        mErrorView.setVisibility(View.GONE);
    }

    @Override
    public void showErrorMessage() {
        mErrorView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(Song song, ActivityOptionsCompat options, Bitmap bitmap) {
        Bundle bundle = new Bundle();
        bundle.putString("name", song.getTitle());
        bundle.putString("description", song.getDescription());
        bundle.putString("type", song.getType());
        bundle.putString("cover", song.getCover());

        Intent intent = new Intent(this, MusicPlayerActivity.class);
        intent.putExtras(bundle);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder mBuilder = getBuilder(song, bitmap, pendingIntent);

        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = mBuilder.build();
        mNotificationManager.notify(001, notification);


//        Intent activityIntent = new Intent(this, MusicPlayerActivity.class);
//        activityIntent.putExtras(bundle);
        startActivity(intent, options.toBundle());
    }

    @NonNull
    private NotificationCompat.Builder getBuilder(Song song, Bitmap bitmap, PendingIntent pendingIntent) {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setContentIntent(pendingIntent);
        mBuilder.setSmallIcon(R.drawable.ic_stat_music_player)
                .setContentTitle(song.getTitle())
                .setContentText(song.getType())
                .setLargeIcon(bitmap)
                .addAction(R.drawable.ic_previous, "Previous", null)
                .addAction(R.drawable.ic_pause, "Pause", null)
                .addAction(R.drawable.ic_next, "Next", null)
                .setStyle(new NotificationCompat.MediaStyle());
        return mBuilder;
    }
}
