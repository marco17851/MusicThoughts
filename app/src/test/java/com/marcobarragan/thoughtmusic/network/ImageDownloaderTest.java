package com.marcobarragan.thoughtmusic.network;

import android.content.Context;
import android.widget.ImageView;

import com.marcobarragan.thoughtmusic.BuildConfig;
import com.marcobarragan.thoughtmusic.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class ImageDownloaderTest {

    Context context;
    ImageDownloader imageDownloader;
    ImageView mockImageView;

    @Before
    public void setup(){
        context = RuntimeEnvironment.application;
        mockImageView = mock(ImageView.class);
        imageDownloader = new ImageDownloader(context);
    }
    
    @Test
    public void shouldNotBeNull(){
        assertNotNull(imageDownloader);
    }

    @Test
    public void shouldLoadImageFromURL(){
        String url = "https://storage.googleapis.com/material-design/publish/material_v_11/assets/0BxFyKV4eeNjDdERRNDc0bkh4UDQ/UI.png";
        RequestCreator mockRequestCreator = mock(RequestCreator.class);
        when(mockRequestCreator.placeholder(R.drawable.broken_image)).thenReturn(mockRequestCreator);

        assertTrue(imageDownloader.loadImageFromUrl(url, mockImageView));
    }

    @Test
    public void shouldReturnFalseIfUrlIsEmpty(){
        String url = "";
        assertFalse(imageDownloader.loadImageFromUrl(url, mockImageView));
    }


    @Test
    public void shouldReturnFalseIfUrlIsNull(){
        String url = null;
        assertFalse(imageDownloader.loadImageFromUrl(url, mockImageView));
    }

    @Test
    public void shouldReturnFalseIfImageViewIsNull(){
        String url = "google.com/fakeimg.jgp";
        ImageView imageView = null;
        assertFalse(imageDownloader.loadImageFromUrl(url, imageView));
    }
}