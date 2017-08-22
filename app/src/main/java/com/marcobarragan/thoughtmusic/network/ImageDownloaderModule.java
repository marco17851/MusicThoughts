package com.marcobarragan.thoughtmusic.network;

import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;

@Module
public class ImageDownloaderModule {
    private Picasso mPicasso;

    public ImageDownloaderModule(Picasso picasso) {
        mPicasso = picasso;
    }

    @Provides
    public Picasso getPicasso(){
        return mPicasso;
    }
}
