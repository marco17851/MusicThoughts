package com.marcobarragan.thoughtmusic.genre;

import com.marcobarragan.thoughtmusic.app.BasePresenter;
import com.marcobarragan.thoughtmusic.app.BaseView;
import com.marcobarragan.thoughtmusic.models.Genre;

import java.util.List;

public interface GenreContract {

    interface View extends BaseView<Presenter>{
        void setGenres(List<Genre> genres);

        void showErrorMessage();
    }

    interface Presenter extends BasePresenter{

    }
}
