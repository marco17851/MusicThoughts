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
        songList.add(new Song("Mambo No. 5", new String[]{"Basic"}, "Hit song from 1999!", 1, "http://storage.googleapis.com/ix_choosemuse/uploads/2016/02/android-logo.png"));
        songList.add(new Song("Who Knew", new String[]{"Stream"}, "One of the best songs by Pink!", 1, "http://storage.googleapis.com/ix_choosemuse/uploads/2016/02/android-logo.png"));

        return songList;
    }

    public static List<Song> getEmptySongs() {
        return null;
    }

    public static Song getSingleSong() {
        return new Song("Mambo No. 5", new String[]{"Basic"}, "Hit song from 1999!", 1, "http://storage.googleapis.com/ix_choosemuse/uploads/2016/02/android-logo.png");
    }
}
