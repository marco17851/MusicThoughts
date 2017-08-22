package com.marcobarragan.thoughtmusic.main;

import com.marcobarragan.thoughtmusic.album.AlbumFragment;
import com.marcobarragan.thoughtmusic.album.AlbumModule;
import com.marcobarragan.thoughtmusic.artist.ArtistFragment;
import com.marcobarragan.thoughtmusic.artist.ArtistModule;
import com.marcobarragan.thoughtmusic.dagger.PerActivity;
import com.marcobarragan.thoughtmusic.genre.GenreFragment;
import com.marcobarragan.thoughtmusic.genre.GenreModule;
import com.marcobarragan.thoughtmusic.network.NetModule;

import dagger.Component;

@PerActivity
@Component(modules = {AlbumModule.class, ArtistModule.class, GenreModule.class, MainModule.class, NetModule.class})
public interface MainComponent {
    void inject(MainActivity mainActivity);
    void inject(AlbumFragment albumFragment);
    void inject(ArtistFragment artistFragment);
    void inject(GenreFragment fragmentView);
}
