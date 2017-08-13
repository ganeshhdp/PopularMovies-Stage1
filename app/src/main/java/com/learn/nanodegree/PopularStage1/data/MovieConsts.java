package com.learn.nanodegree.PopularStage1.data;

/**
 * Created by ganesh on 8/6/17.
 */

public class MovieConsts {

    public static final String MOVIE_URL = "http://api.themoviedb.org/3/movie/";
    public static final String API_KEY="";
    public static final String MOVIE_POPULAR = "popular";
    public static final String MOVIE_TOP_RATED = "top_rated";
    public static final String VOTE_COUNT = "vote_count";
    public static final String VOTE_AVERAGE = "vote_average";
    public static final String TITLE = "title";
    public static final String POSTER_PATH = "poster_path";
    public static final String ORIGINAL_TITLE = "original_title";
    public static final String BACKDROP_PATH = "backdrop_path";
    public static final String OVERVIEW = "overview";
    public static final String RELEASE_DATE = "release_date";
    public static final String POSTER_URL = "http://image.tmdb.org/t/p/w185//";


    public static String getUrlString(boolean sort_order) {
        if(sort_order) {
            return MOVIE_URL+MOVIE_TOP_RATED;
        } else {
            return MOVIE_URL+MOVIE_POPULAR;
        }
    }
}
