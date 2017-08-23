package com.marcobarragan.thoughtmusic.network;

import android.content.Context;

import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;

@Module
public class ImageDownloaderModule {
    private Context context;

    public ImageDownloaderModule(Context context){
        this.context = context;
    }

    @Provides
    Context provideContext(){
        return this.context;
    }

    @Provides
    ImageDownloader provideImageDownloader(Context context){
        return new ImageDownloader(context);
    }
}
