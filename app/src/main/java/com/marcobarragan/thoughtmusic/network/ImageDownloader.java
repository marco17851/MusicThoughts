package com.marcobarragan.thoughtmusic.network;

import android.content.Context;
import android.widget.ImageView;

import com.marcobarragan.thoughtmusic.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import javax.inject.Inject;

public class ImageDownloader {

    private Context context;

    @Inject
    public ImageDownloader(Context context){
        this.context = context;
    }

    public boolean loadImageFromUrl(String url, ImageView imageView){
        if (url == null || url.isEmpty() || imageView == null){
            return false;
        }

        RequestCreator requestCreator = Picasso.with(context).load(url);
        RequestCreator load = requestCreator.placeholder(R.drawable.broken_image);
        load.into(imageView);

        return true;
    }
}
