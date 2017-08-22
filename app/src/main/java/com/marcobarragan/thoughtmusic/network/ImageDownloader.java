package com.marcobarragan.thoughtmusic.network;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import javax.inject.Inject;

public class ImageDownloader {

    private Picasso mPicasso;

    @Inject
    public ImageDownloader(Picasso picasso){
        mPicasso = picasso;
    }

    public void loadImageFromUrl(String url, ImageView imageView){
        RequestCreator load = mPicasso.load(url);
        load.into(imageView);
    }
}
