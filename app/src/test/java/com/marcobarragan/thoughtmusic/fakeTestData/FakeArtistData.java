package com.marcobarragan.thoughtmusic.fakeTestData;

import com.marcobarragan.thoughtmusic.models.Artist;

import java.util.ArrayList;
import java.util.List;

public class FakeArtistData {
    public static List<Artist> getSampleArtists() {
        List<Artist> artists = new ArrayList<>();

        List<Integer> songIds = new ArrayList<>();
        songIds.add(1);
        songIds.add(2);
        songIds.add(3);

        artists.add(new Artist("Pop", "0", songIds, "cover"));
        artists.add(new Artist("Rap", "1", songIds, "cover"));
        artists.add(new Artist("Disco", "2", songIds, "cover"));
        return artists;
    }

    public static List<Artist> getEmptyArtists() {
        return new ArrayList<Artist>();
    }
}
