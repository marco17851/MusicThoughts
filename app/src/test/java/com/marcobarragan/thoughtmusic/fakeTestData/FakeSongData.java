package com.marcobarragan.thoughtmusic.fakeTestData;


import com.marcobarragan.thoughtmusic.models.Song;

import java.util.ArrayList;
import java.util.List;

public class FakeSongData {

    public static List<Integer> getSampleSongIds() {
        List<Integer> songIds = new ArrayList<>();
        songIds.add(1);
        songIds.add(2);
        songIds.add(3);

        return songIds;
    }

    public static List<Integer> getSingleSongId() {
        List<Integer> songIds = new ArrayList<>();
        songIds.add(1);

        return songIds;
    }

    public static List<Song> getSampleSongs(){
        List<Song> songList = new ArrayList<>();
        songList.add(new Song("Mambo No. 5", "Basic", "Hit song from 1999!"));
        songList.add(new Song("Who Knew", "Stream", "One of the best songs by Pink!"));

        return songList;
    }

    public static List<Song> getEmptySongs() {
        return null;
    }
}
