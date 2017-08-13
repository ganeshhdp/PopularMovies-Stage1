package com.example.ganesh.popular_movies_stage_1.View;

import android.content.ContentValues;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import com.example.ganesh.popular_movies_stage_1.R;
import com.example.ganesh.popular_movies_stage_1.Utils.BackgroundFetchTask;
import com.example.ganesh.popular_movies_stage_1.data.MovieAdapter;
import com.example.ganesh.popular_movies_stage_1.data.MovieConsts;
import com.example.ganesh.popular_movies_stage_1.data.MovieInfo;

import java.util.Vector;

public class WelcomeActivity extends AppCompatActivity implements MovieAdapter.MovieClickListener{

    GridView mGridView;
    MovieAdapter mAdapter;
    MovieInfo[] movieInfoList;
    BackgroundFetchTask.Delegate delegate;
    Toolbar toolBar;
    TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        setUpTabs();
        RecyclerView gridView = (RecyclerView) findViewById(R.id.rv_posters);
        RecyclerView.ItemAnimator animator = new DefaultItemAnimator();
        animator.setAddDuration(3000);
        int numOfCols = 2;
        GridLayoutManager layoutManager;
        layoutManager = new GridLayoutManager(this,2);
        gridView.setLayoutManager(layoutManager);
        gridView.setHasFixedSize(true);
        mAdapter = new MovieAdapter(this);
        gridView.setAdapter(mAdapter);
        loadMovieData(false);

    }

    private void setUpTabs() {
        toolBar = (Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        setSupportActionBar(toolBar);
        tabLayout.addTab(tabLayout.newTab().setText(getResources().getString(R.string.popular)));
        tabLayout.addTab(tabLayout.newTab().setText(getResources().getString(R.string.top_rated)));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getText().equals(getResources().getString(R.string.popular))){
                    loadMovieData(false);
                }
                if(tab.getText().equals(getResources().getString(R.string.top_rated))){
                    loadMovieData(true);
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    public void loadMovieData(boolean sort) {
      BackgroundFetchTask.Delegate delegate = new BackgroundFetchTask.Delegate() {
          @Override
          public void onMovieDataDelegated(Vector<ContentValues> contentValues) {
              loadMovies(contentValues);
              mAdapter.setMovieData(movieInfoList);
          }
      };
      BackgroundFetchTask mTask = new BackgroundFetchTask(delegate);
        mTask.sortOrder(sort);
        mTask.execute();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMovieSelected(int pos) {
        Intent intent=new Intent(this,MovieDetailActivity.class);
        Bundle b = new Bundle();
        b.putParcelable("Movie",movieInfoList[pos]);
        intent.putExtras(b);
        startActivity(intent);
    }

    private void loadMovies(Vector<ContentValues> contentValues) {
        movieInfoList = new MovieInfo[contentValues.capacity()];
        for(int i=0;i<movieInfoList.length;i++){movieInfoList[i] = new MovieInfo();
            ContentValues content = contentValues.get(i);
            movieInfoList[i].setMtitle(content.getAsString(MovieConsts.TITLE));
            movieInfoList[i].setBackdrop_path(content.getAsString(MovieConsts.BACKDROP_PATH));
            movieInfoList[i].setOriginal_title(content.getAsString(MovieConsts.ORIGINAL_TITLE));
            movieInfoList[i].setmOverView(content.getAsString(MovieConsts.OVERVIEW));
            movieInfoList[i].setPoster_path(content.getAsString(MovieConsts.POSTER_PATH));
            movieInfoList[i].setVote_count(content.getAsInteger(MovieConsts.VOTE_COUNT));
            movieInfoList[i].setmReleaseDate(content.getAsString(MovieConsts.RELEASE_DATE));
            movieInfoList[i].setVote_average(content.getAsFloat(MovieConsts.VOTE_AVERAGE));
        }
    }


}
