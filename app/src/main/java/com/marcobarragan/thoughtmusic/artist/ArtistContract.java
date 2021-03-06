package com.marcobarragan.thoughtmusic.artist;

import com.marcobarragan.thoughtmusic.app.BasePresenter;
import com.marcobarragan.thoughtmusic.app.BaseView;
import com.marcobarragan.thoughtmusic.models.Artist;

import java.util.List;

public interface ArtistContract {
    interface View extends BaseView<Presenter> {
        void setArtists(List<Artist> artists);

        void showErrorMessage();
    }

    interface Presenter extends BasePresenter {

    }
}
