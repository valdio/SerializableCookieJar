package com.valdio.cookiejar.serializablecookiejar;

import java.io.Serializable;

import okhttp3.Cookie;

/**
 * Created by Valdio Veliu on 10/02/2017.
 */

public class SerializableCookie implements Serializable {

    private String name;
    private String value;
    private long expiresAt;
    private String domain;
    private String path;
    private boolean secure;
    private boolean httpOnly;
    private boolean persistent; // True if 'expires' or 'max-age' is present.
    private boolean hostOnly; // True unless 'domain' is present.


    public SerializableCookie(Cookie cookie) {
        this.name = cookie.name();
        this.value = cookie.value();
        this.expiresAt = cookie.expiresAt();
        this.domain = cookie.domain();
        this.path = cookie.path();
        this.secure = cookie.secure();
        this.hostOnly = cookie.httpOnly();
        this.hostOnly = cookie.hostOnly();
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public long getExpiresAt() {
        return expiresAt;
    }

    public String getDomain() {
        return domain;
    }

    public String getPath() {
        return path;
    }

    public boolean isSecure() {
        return secure;
    }

    public boolean isHttpOnly() {
        return httpOnly;
    }

    public boolean isPersistent() {
        return persistent;
    }

    public boolean isHostOnly() {
        return hostOnly;
    }
}
