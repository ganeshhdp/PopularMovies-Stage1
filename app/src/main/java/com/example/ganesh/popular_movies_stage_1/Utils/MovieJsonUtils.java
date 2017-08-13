package com.example.ganesh.popular_movies_stage_1.Utils;

import android.content.ContentValues;

import com.example.ganesh.popular_movies_stage_1.data.MovieConsts;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Vector;

/**
 * Created by ganesh on 8/9/17.
 */

public class MovieJsonUtils {

    /**
     * This method returns the vector with all the movie details in the content values
     * @param json
     * @return
     */

    private int vote_count;
    private float vote_average;
    private String mtitle;
    private String poster_path;
    private String original_title;
    private String backdrop_path;
    private String mOverView;
    private String mReleaseDate;
    final static String MOVIE_RESULT = "results";
    public static Vector<ContentValues> getMovieDataFromJsonString ( String json ) throws JSONException {
        Vector mVector = new Vector();
        JSONObject movieJson = new JSONObject(json);
        JSONArray weatherArray = movieJson.getJSONArray(MOVIE_RESULT);
        for (int i = 0; i < weatherArray.length(); i++) {
            JSONObject movieData = weatherArray.getJSONObject(i);
            ContentValues contentValues = new ContentValues();
            contentValues.put(MovieConsts.VOTE_COUNT,movieData.getInt(MovieConsts.VOTE_COUNT));
            contentValues.put(MovieConsts.VOTE_AVERAGE,movieData.getString(MovieConsts.VOTE_AVERAGE));
            contentValues.put(MovieConsts.TITLE,movieData.getString(MovieConsts.TITLE));
            contentValues.put(MovieConsts.POSTER_PATH,movieData.getString(MovieConsts.POSTER_PATH));
            contentValues.put(MovieConsts.ORIGINAL_TITLE,movieData.getString(MovieConsts.ORIGINAL_TITLE));
            contentValues.put(MovieConsts.BACKDROP_PATH,movieData.getString(MovieConsts.BACKDROP_PATH));
            contentValues.put(MovieConsts.OVERVIEW,movieData.getString(MovieConsts.OVERVIEW));
            contentValues.put(MovieConsts.RELEASE_DATE,movieData.getString(MovieConsts.RELEASE_DATE));
            mVector.add(contentValues);
        }
        return mVector;
    }
}
