package com.valdio.cookiejar.serializablecookiejar;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Valdio Veliu on 10/02/2017.
 */

public class SerializableCookieStorage {

    private final String STORAGE_NAME = "com.CookieJar.SerializableCookie.SerializableCookieStorage";
    private SharedPreferences preferences;
    private Context context;

    public SerializableCookieStorage(Context context) {
        this.context = context;
    }


    public void storeSerializableCookies(ArrayList<SerializableCookie> list) {
        //check if there are previously added cookies, append if this is true
        ArrayList<SerializableCookie> existingCookies = loadSerializableCookies();
        if (existingCookies != null) {
            for (SerializableCookie serializableCookie : list)
                existingCookies.add(serializableCookie);//append new cookies
        } else {
            existingCookies = list;
        }
        preferences = context.getSharedPreferences(STORAGE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(existingCookies);
        editor.putString("SerializableCookies", json);
        editor.apply();
    }

    public ArrayList<SerializableCookie> loadSerializableCookies() {
        preferences = context.getSharedPreferences(STORAGE_NAME, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = preferences.getString("SerializableCookies", null);
        Type type = new TypeToken<ArrayList<SerializableCookie>>() {
        }.getType();
        return gson.fromJson(json, type);
    }


    public void clearAllCookies() {
        preferences = context.getSharedPreferences(STORAGE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
    }

}
