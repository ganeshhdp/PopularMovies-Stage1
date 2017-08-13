package com.example.ganesh.popular_movies_stage_1.Utils;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Scanner;

import com.example.ganesh.popular_movies_stage_1.data.MovieConsts;

/**
 * Created by ganesh on 7/31/17.
 */

public class NetworkUtil {

    private static final String MOVIE_URL ="https://www.themoviedb.org/3/movie/" ;
    private static final String LOGTAG = "NetworkUtils";

    public static URL buildUrl(boolean sort_order) throws MalformedURLException {

            Uri buildUri = Uri.parse(MovieConsts.getUrlString(sort_order)).buildUpon()
                    .appendQueryParameter("api_key", MovieConsts.API_KEY).build();
        URL mUrl = null;
        try {
            mUrl = new URL(buildUri.toString());
        }
        catch (MalformedURLException exception){
            exception.printStackTrace();
        }
        Log.d(LOGTAG,"movie URL::"+mUrl.toString());
        return mUrl;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
