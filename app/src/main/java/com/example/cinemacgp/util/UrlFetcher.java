package com.example.cinemacgp.util;

import java.net.MalformedURLException;
import java.net.URL;

public class UrlFetcher {
    private final static String BASE_URL="https://api.jikan.moe/v4";

    public static String getStringUrl(String endPoint) {
        String url = BASE_URL + endPoint;
        return url;
    }
}
