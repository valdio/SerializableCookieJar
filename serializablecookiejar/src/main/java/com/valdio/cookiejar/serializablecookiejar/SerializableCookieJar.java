package com.valdio.cookiejar.serializablecookiejar;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * Created by Valdio Veliu on 10/02/2017.
 */

public class SerializableCookieJar implements CookieJar {

    private List<Cookie> cookies;
    private Context context;

    public SerializableCookieJar(Context context) {
        this.context = context;
    }

    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        this.cookies = cookies;
        saveSerializableCookies();
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        if (cookies != null)
            return cookies;
        return loadSerializableCookies();
    }

    /**
     * Method to store {@link Cookie}s in {@link android.content.SharedPreferences}
     */
    private void saveSerializableCookies() {
        ArrayList<SerializableCookie> serializableCookies = new ArrayList<>();
        if (cookies != null && cookies.size() > 0)
            for (int i = 0; i < cookies.size(); i++) {
                //convert cookies to SerializableCookie objects and add them to an ArrayList
                serializableCookies.add(new SerializableCookie(cookies.get(i)));
            }

        //store cookies is SharedPreferences.
        if (serializableCookies.size() > 0)
            new SerializableCookieStorage(context).storeSerializableCookies(serializableCookies);
    }

    /**
     * Method to load stored cookies from {@link android.content.SharedPreferences}
     *
     * @return ArrayList of {@link Cookie}
     */
    private ArrayList<Cookie> loadSerializableCookies() {
        ArrayList<Cookie> cookies = new ArrayList<>();
        ArrayList<SerializableCookie> serializableCookies = new SerializableCookieStorage(context).loadSerializableCookies();
        if (serializableCookies != null)
            for (SerializableCookie serializableCookie : serializableCookies) {
                Cookie cookie = buildCookie(serializableCookie);
                if (cookie != null)
                    cookies.add(cookie);
//                Log.d("Cookie", serializableCookie.getPath());
//                Log.d("Cookie", serializableCookie.getDomain());
//                Log.d("Cookie", serializableCookie.getValue());
//                Log.d("Cookie", String.valueOf(serializableCookie.isSecure()));
//                Log.d("Cookie", String.valueOf(serializableCookie.isPersistent()));
//                Log.d("Cookie", String.valueOf(serializableCookie.getExpiresAt()));
            }
        return cookies;
    }

    /**
     * Method to build a OkHttp {@link Cookie} from a {@link SerializableCookie} object
     *
     * @param serializableCookie cookie stored in {@link android.content.SharedPreferences}
     * @return {@link Cookie}
     */
    private Cookie buildCookie(SerializableCookie serializableCookie) {
        Cookie cookie = null;
        if (serializableCookie.isSecure() && serializableCookie.isHttpOnly()) {
            cookie = new Cookie.Builder()
                    .name(serializableCookie.getName())
                    .value(serializableCookie.getValue())
                    .expiresAt(serializableCookie.getExpiresAt())
                    .domain(serializableCookie.getDomain())
                    .path(serializableCookie.getPath())
                    .secure()
                    .httpOnly()
                    .build();
        } else if (serializableCookie.isSecure() && !serializableCookie.isHttpOnly()) {
            cookie = new Cookie.Builder()
                    .name(serializableCookie.getName())
                    .value(serializableCookie.getValue())
                    .expiresAt(serializableCookie.getExpiresAt())
                    .domain(serializableCookie.getDomain())
                    .path(serializableCookie.getPath())
                    .secure()
                    .build();
        } else if (!serializableCookie.isSecure() && serializableCookie.isHttpOnly()) {
            cookie = new Cookie.Builder()
                    .name(serializableCookie.getName())
                    .value(serializableCookie.getValue())
                    .expiresAt(serializableCookie.getExpiresAt())
                    .domain(serializableCookie.getDomain())
                    .path(serializableCookie.getPath())
                    .httpOnly()
                    .build();
        } else if (!serializableCookie.isSecure() && !serializableCookie.isHttpOnly()) {
            cookie = new Cookie.Builder()
                    .name(serializableCookie.getName())
                    .value(serializableCookie.getValue())
                    .expiresAt(serializableCookie.getExpiresAt())
                    .domain(serializableCookie.getDomain())
                    .path(serializableCookie.getPath())
                    .build();
        }
        return cookie;
    }

    /**
     * Method to clear all expired Cookies from {@link android.content.SharedPreferences}
     * <p>
     * Method needs rechecking - Not tested properly
     */
    public void clearExpiredCookies() {
        SerializableCookieStorage cookieStorage = new SerializableCookieStorage(context);
        ArrayList<SerializableCookie> serializableCookies = cookieStorage.loadSerializableCookies();
        if (serializableCookies != null)
            for (SerializableCookie cookie : serializableCookies) {
                if (!cookie.isPersistent()) {
                    /** Means that this cookie expires at the end of the current session. */
                    serializableCookies.remove(cookie);
                }
            }

        cookieStorage.clearAllCookies();//clear all
        cookieStorage.storeSerializableCookies(serializableCookies);//save only the valid cookies
    }

    /**
     * Method to clear all Cookies from {@link android.content.SharedPreferences}
     */
    public void clearCookies() {
        new SerializableCookieStorage(context).clearAllCookies();
    }
}
