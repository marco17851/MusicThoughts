package com.marcobarragan.thoughtmusic.artist;

import com.marcobarragan.thoughtmusic.app.BasePresenter;
import com.marcobarragan.thoughtmusic.app.BaseView;
import com.marcobarragan.thoughtmusic.models.Artist;

import java.util.List;

public class ArtistContract {
    interface View extends BaseView<Presenter> {
        void setArtist(List<Artist> artists);

        void showErrorMessage();
    }

    interface Presenter extends BasePresenter {

    }
}
