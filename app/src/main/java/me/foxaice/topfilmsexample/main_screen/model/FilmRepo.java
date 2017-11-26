package me.foxaice.topfilmsexample.main_screen.model;

import android.content.Context;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import me.foxaice.topfilmsexample.R;

public class FilmRepo {
    public static List<Film> loadFilm(Context context) {
        Set<Film> filmsSet = new TreeSet<>();
        JsonReader jsonReader = new JsonReader(new InputStreamReader(context.getResources().openRawResource(R.raw.films)));
        try {
            jsonReader.beginObject();
            jsonReader.nextName();
            JsonParser parser = new JsonParser();
            JsonElement object = parser.parse(jsonReader);
            JsonArray array = object.isJsonArray() ? object.getAsJsonArray() : null;
            if (array != null) {
                for (Object item : array) {
                    JsonObject obj = (JsonObject) item;
                    CharSequence localName = obj.get("localized_name").getAsString();
                    CharSequence originalName = obj.get("name").getAsString();
                    CharSequence imageUrl = obj.get("image_url").getAsString();
                    CharSequence description = obj.get("description").getAsString();
                    double rating = obj.get("rating").getAsDouble();
                    short year = obj.get("year").getAsShort();
                    filmsSet.add(new Film(localName, originalName, imageUrl, description, rating, year));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                jsonReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<>(filmsSet);
    }
}
