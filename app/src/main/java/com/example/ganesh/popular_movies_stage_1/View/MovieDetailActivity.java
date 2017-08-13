package com.example.ganesh.popular_movies_stage_1.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ganesh.popular_movies_stage_1.R;
import com.example.ganesh.popular_movies_stage_1.data.MovieConsts;
import com.example.ganesh.popular_movies_stage_1.data.MovieInfo;
import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends AppCompatActivity {

    MovieInfo selectedMovie;
    ImageView backdrop;
    TextView title;
    ImageView poster_view;
    TextView release_date;
    TextView vote_average;
    TextView overView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        backdrop = (ImageView)findViewById(R.id.backdrop);
        poster_view = (ImageView)findViewById(R.id.movie_thumbnail);
        release_date = (TextView)findViewById(R.id.release_date);
        vote_average = (TextView)findViewById(R.id.vote_average);
        overView = (TextView) findViewById(R.id.overview);
        title = (TextView)findViewById(R.id.original_title);
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        if(b!=null) {
            selectedMovie = b.getParcelable("Movie");
        }
        setupMovieDetails();
    }

    private void setupMovieDetails() {
        Picasso.with(this).load( MovieConsts.POSTER_URL + selectedMovie.getBackdrop_path()).into(backdrop);
        Picasso.with(this).load( MovieConsts.POSTER_URL + selectedMovie.getPoster_path()).into(poster_view);
        title.setText(getResources().getString(R.string.title_string)+selectedMovie.getOriginal_title());
        release_date.setText(getResources().getString(R.string.release_string)+selectedMovie.getmReleaseDate());
        vote_average.setText(getResources().getString(R.string.rating_string)+selectedMovie.getVote_average()+"/10");
        overView.setText(selectedMovie.getmOverView());
    }
}
