package com.marcobarragan.thoughtmusic.network;

import android.content.Context;
import android.widget.ImageView;

import com.marcobarragan.thoughtmusic.BuildConfig;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class ImageDownloaderTest {

    Picasso mockPicasso;
    ImageDownloader imageDownloader;
    ImageView mockImageView;

    @Before
    public void setup(){
        mockPicasso = mock(Picasso.class);
        mockImageView = mock(ImageView.class);
        imageDownloader = new ImageDownloader(mockPicasso);
    }
    
    @Test
    public void shouldNotBeNull(){
        assertNotNull(imageDownloader);
    }

    @Test
    public void shouldLoadImageFromURL(){
        String url = "https://storage.googleapis.com/material-design/publish/material_v_11/assets/0BxFyKV4eeNjDdERRNDc0bkh4UDQ/UI.png";
        RequestCreator mockRequestCreator = mock(RequestCreator.class);

        when(mockPicasso.load(url)).thenReturn(mockRequestCreator);

        imageDownloader.loadImageFromUrl(url, mockImageView);

        verify(mockPicasso).load(url);
        verify(mockRequestCreator).into(mockImageView);
    }
}