package com.example.ganesh.popular_movies_stage_1.Utils;

import android.content.ContentValues;
import android.os.AsyncTask;

import org.json.JSONException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.Vector;

/**
 * Created by ganesh on 8/9/17.
 */

    public class BackgroundFetchTask extends AsyncTask<Void, Void, Vector<ContentValues>> {



    public interface Delegate {
            void onMovieDataDelegated(Vector<ContentValues> contentValues) ;
        }

        Delegate delegate = null;
        public boolean sortOrder;

        public BackgroundFetchTask(Delegate delegate){
            this.delegate = delegate;
        }

        public void sortOrder(
            boolean sort) {
            sortOrder = sort;
        }
        @Override
        protected Vector<ContentValues> doInBackground(Void... params) {
            Vector<ContentValues> vector = null;
            try {
                URL mUrl = NetworkUtil.buildUrl(sortOrder);
                String result = NetworkUtil.getResponseFromHttpUrl(mUrl);
                vector = MovieJsonUtils.getMovieDataFromJsonString(result);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return vector;
        }

        @Override
        protected void onPostExecute(Vector<ContentValues> contentValues) {
            super.onPostExecute(contentValues);
            delegate.onMovieDataDelegated(contentValues);
        }
    }

