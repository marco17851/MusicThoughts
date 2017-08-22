package com.marcobarragan.thoughtmusic.album;

import com.marcobarragan.thoughtmusic.app.BasePresenter;
import com.marcobarragan.thoughtmusic.app.BaseView;
import com.marcobarragan.thoughtmusic.models.Album;

import java.util.List;

public interface AlbumContract {
    interface View extends BaseView<Presenter> {
        void setAlbums(List<Album> albums);

        void showErrorMessage();
    }

    interface Presenter extends BasePresenter {

    }
}
