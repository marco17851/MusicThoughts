package com.marcobarragan.thoughtmusic.songs;

import com.marcobarragan.thoughtmusic.app.BasePresenter;
import com.marcobarragan.thoughtmusic.app.BaseView;
import com.marcobarragan.thoughtmusic.models.Song;

import java.util.List;

public interface SongsContract {
    interface View extends BaseView<Presenter> {
        void setSongs(List<Song> songs);
        void showErrorMessage();
    }

    interface Presenter extends BasePresenter {

    }
}
