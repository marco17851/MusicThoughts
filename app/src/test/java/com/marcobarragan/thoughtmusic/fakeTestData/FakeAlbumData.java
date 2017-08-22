package com.marcobarragan.thoughtmusic.fakeTestData;

import com.marcobarragan.thoughtmusic.models.Album;

import java.util.ArrayList;
import java.util.List;

public class FakeAlbumData {
    public static List<Album> getSingleAlbum() {
        List<Album> albums = new ArrayList<>();

        List<Integer> songIds = new ArrayList<>();
        songIds.add(1);

        albums.add(new Album("Album1", "0", songIds, "cover"));
        return albums;
    }

    public static List<Album> getSampleAlbums() {
        List<Album> albums = new ArrayList<>();

        List<Integer> songIds = new ArrayList<>();
        songIds.add(1);
        songIds.add(2);
        songIds.add(3);

        albums.add(new Album("Album1", "0", songIds, "cover"));
        albums.add(new Album("Album2", "1", songIds, "cover"));
        albums.add(new Album("Album3", "2", songIds, "cover"));
        return albums;
    }

    public static List<Album> getEmptyAlbums() {
        return new ArrayList<Album>();
    }
}
