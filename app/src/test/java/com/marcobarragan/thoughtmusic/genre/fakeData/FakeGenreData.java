package com.marcobarragan.thoughtmusic.genre.fakeData;

import com.marcobarragan.thoughtmusic.models.Genre;

import java.util.ArrayList;
import java.util.List;

public class FakeGenreData {

    public static List<Genre> getSampleGenres() {
        List<Genre> genres = new ArrayList<>();

        List<Integer> songIds = new ArrayList<>();
        songIds.add(1);
        songIds.add(2);
        songIds.add(3);

        genres.add(new Genre("Pop", "0", songIds));
        genres.add(new Genre("Rap", "1", songIds));
        genres.add(new Genre("Disco", "2", songIds));
        return genres;
    }

    public static List<Genre> getEmptyGenres() {
        return new ArrayList<Genre>();
    }

    public static List<Genre> getSingleGenre() {
        List<Genre> genre = new ArrayList<>();
        List<Integer> songIds = new ArrayList<>();
        songIds.add(1);

        genre.add(new Genre("Pop", "0", songIds));

        return genre;
    }
}
