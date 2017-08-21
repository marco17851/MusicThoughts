package com.marcobarragan.thoughtmusic.fakeData;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.marcobarragan.thoughtmusic.models.Genre;
import com.marcobarragan.thoughtmusic.models.Song;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FakeDataRepository {

    public static List<Genre> getGenres() throws JSONException {
        JsonElement element = new JsonParser().parse(getGenreJsonString());
        JsonArray jsonArray = (JsonArray) element.getAsJsonObject().get("genres");

        Iterator iterator = jsonArray.iterator();

        List<Genre> list = new ArrayList<>();

        while (iterator.hasNext()){
            JsonObject item = (JsonObject) iterator.next();
            String title = item.get("title").getAsString();
            String id = item.get("id").getAsString();
            JsonArray songIds = item.get("song_ids").getAsJsonArray();
            List<Integer> songIdsList = new ArrayList<>();
            Iterator songIterator = songIds.iterator();

            while (songIterator.hasNext()){
                JsonPrimitive songId = (JsonPrimitive) songIterator.next();
                songIdsList.add(songId.getAsInt());
            }

//            Genre genre = new Genre(title, id, songIdsList);
//            list.add(genre);
        }
        return list;
    }

    public static List<Song> getSongs(){
        List<Song> songList = new ArrayList<>();
        songList.add(new Song("Mambo No. 5", "Basic", "Hit song from 1999!"));
        songList.add(new Song("Who Knew", "Stream", "One of the best songs by Pink!"));
        songList.add(new Song("Poker Face", "Basic", "Ma ma ma ma"));
        songList.add(new Song("I Love You", "Artist", "Barney's only hit single"));


        return songList;
    }

    private static String getGenreJsonString() {
        return "{\n" +
                "  \"genres\": [\n" +
                "    {\n" +
                "      \"id\": 1,\n" +
                "      \"title\": \"Pop\",\n" +
                "      \"song_ids\": [1,2,3,4,5]\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 2,\n" +
                "      \"title\": \"Rock\",\n" +
                "      \"song_ids\": [6,7,8,9,10]\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 3,\n" +
                "      \"title\": \"Rap\",\n" +
                "      \"song_ids\": [6,7,8,9,10]\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 4,\n" +
                "      \"title\": \"Hip-Hop\",\n" +
                "      \"song_ids\": [11,12,13,14,15]\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 5,\n" +
                "      \"title\": \"Disco\",\n" +
                "      \"song_ids\": [16,17,18,19,20]\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 6,\n" +
                "      \"title\": \"Classical\",\n" +
                "      \"song_ids\": [21,22,23,24,25]\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 7,\n" +
                "      \"title\": \"Electronic\",\n" +
                "      \"song_ids\": [26,27,28,29,30]\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 8,\n" +
                "      \"title\": \"K-Pop\",\n" +
                "      \"song_ids\": [31,32,33,34,35]\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 9,\n" +
                "      \"title\": \"Country\",\n" +
                "      \"song_ids\": [36,37,38,39,40]\n" +
                "    }\n" +
                "  ]\n" +
                "}";
    }
}
