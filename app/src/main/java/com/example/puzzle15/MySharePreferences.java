package com.example.puzzle15;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MySharePreferences {
    private static SharedPreferences sharedPreferences;
    private static MySharePreferences mySharePreferences;

    private MySharePreferences() {
    }

    public static MySharePreferences getInstance(Context context) {
        if (mySharePreferences == null) {
            mySharePreferences = new MySharePreferences();
            sharedPreferences = context.getSharedPreferences("puzzle_15", Context.MODE_PRIVATE);
        }
        return mySharePreferences;
    }

    public void myPut(String key, int pos) {
        sharedPreferences.edit().putInt(key, pos).apply();
    }

    public void myPut(String key, boolean pos) {
        sharedPreferences.edit().putBoolean(key, pos).apply();
    }

    public void myPut(String key, String pos) {
        sharedPreferences.edit().putString(key, pos).apply();
    }

    public void myPut(String key, long pos) {
        sharedPreferences.edit().putLong(key, pos).apply();
    }

    public int getInt(String key, int defaultValue) {
        return sharedPreferences.getInt(key, defaultValue);
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return sharedPreferences.getBoolean(key, defaultValue);
    }

    public String getString(String key, String defaultValue) {
        return sharedPreferences.getString(key, defaultValue);
    }

    public long getLong(String key, long defaultValue) {
        return sharedPreferences.getLong(key, defaultValue);
    }

    private final String rating = "RATING";
    private final String splitRatings = "&";
    private final String splitData = "#";

    public List<RatingData> getRating() {
        String allRate = getString(rating, "");
        if (allRate.isEmpty()) return new ArrayList<>();
        List<RatingData> list = new ArrayList<>();
        String[] arr = allRate.split(splitRatings);
        for (String s : arr) {
            String[] str = s.split(splitData);
            list.add(
                    new RatingData(
                            Long.parseLong(str[0]),
                            Long.parseLong(str[1]),
                            Long.parseLong(str[2])
                    )
            );
        }
        Log.d("ARR", "get: " + list);
        Log.d("ARR", "get str: " + allRate);
        return list;
    }

    public void putRating(long time, long move) {
        List<RatingData> ratingData = getRating();
        RatingData newRating = new RatingData(time, System.currentTimeMillis(), move);
        ratingData.add(newRating);
        Collections.sort(ratingData, (t1, t2) -> Long.compare(t1.time + t1.move, t2.move + t2.time));
        int size = ratingData.size();
        ratingData = ratingData.subList(0, Math.min(size, 3));
        Log.d("ARR", "putRating: " + ratingData);
        String replace = ratingData.toString().replace(" ", "").replace("[", "").replace("]", "").replace(",", splitRatings) + splitRatings;
        Log.d("ARR", "putRating: " + replace);
        myPut(rating, replace);
    }
}
