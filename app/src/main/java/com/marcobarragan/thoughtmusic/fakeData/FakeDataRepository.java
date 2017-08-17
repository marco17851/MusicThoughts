package com.marcobarragan.thoughtmusic.fakeData;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.google.gson.internal.ObjectConstructor;
import com.marcobarragan.thoughtmusic.models.Genre;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FakeDataRepository {

    public static List<Genre> getGenres() throws JSONException {
        FileReader file = null;
        try {
            file = new FileReader("./genres.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        JsonElement element = new JsonParser().parse(file);
        JsonArray jsonArray = (JsonArray) element.getAsJsonObject().get("genres");

        System.out.println(jsonArray);
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

            Genre genre = new Genre(title, id, songIdsList);
            list.add(genre);
        }
        return list;
    }
}
