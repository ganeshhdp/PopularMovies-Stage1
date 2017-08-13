package com.learn.nanodegree.PopularStage1.data;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.learn.nanodegree.PopularStage1.R;
import com.squareup.picasso.Picasso;

/**
 * Created by ganesh on 7/24/17.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {

    private static final String LOGTAG = "MovieAdapter";
    private MovieInfo[] movieList;
    Context context;
    int lastPosition;

    private final MovieClickListener movieClickListener;

    public interface MovieClickListener {
        void onMovieSelected(int pos);
    }

    public MovieAdapter(MovieClickListener listener){
        movieClickListener = listener;
    }

    @Override
    public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_item,parent,false);
        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieHolder holder, int position) {
        holder.bindView(position);
        animate(holder);
    }

    private void animate(MovieHolder holder) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(holder.itemView,"alpha",0,1);
        animator.setDuration(2500);
        animator.start();
    }

    @Override
    public int getItemCount() {

        if (movieList == null ) return 0;
        return movieList.length;
    }

    public void setMovieData(MovieInfo[] movieInfoList) {
        lastPosition=0;
        this.movieList = movieInfoList;
        notifyDataSetChanged();
    }

    public class MovieHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        View itemView;
        public MovieHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            itemView.setOnClickListener(this);
        }
       public void bindView(int pos) {
           ImageView image = (ImageView) itemView.findViewById(R.id.movie_image);
           String imagePath = movieList[pos].getPoster_path();
           imagePath = MovieConsts.POSTER_URL+imagePath;
           Picasso.with(context).load(imagePath).into(image);
       }

        @Override
        public void onClick(View v) {
            movieClickListener.onMovieSelected(getAdapterPosition());
        }
    }
}
