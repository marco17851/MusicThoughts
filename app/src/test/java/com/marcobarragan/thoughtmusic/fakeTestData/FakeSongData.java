package com.marcobarragan.thoughtmusic.fakeTestData;


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
}
