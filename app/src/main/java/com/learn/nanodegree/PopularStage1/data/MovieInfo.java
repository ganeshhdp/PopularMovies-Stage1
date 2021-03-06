package com.learn.nanodegree.PopularStage1.data;
import android.os.Parcel;
import android.os.Parcelable;
/**
 * Created by ganesh on 8/7/17.
 */

public class MovieInfo implements Parcelable {

    private int vote_count;
    private float vote_average;
    private String mtitle;
    private String poster_path;
    private String original_title;
    private String backdrop_path;
    private String mOverView;
    private String mReleaseDate;

    protected MovieInfo(Parcel in) {
        vote_count = in.readInt();
        vote_average = in.readFloat();
        mtitle = in.readString();
        poster_path = in.readString();
        original_title = in.readString();
        backdrop_path = in.readString();
        mOverView = in.readString();
        mReleaseDate = in.readString();
    }

    public MovieInfo(){

    }

    public static final Creator<MovieInfo> CREATOR = new Creator<MovieInfo>() {
        @Override
        public MovieInfo createFromParcel(Parcel in) {
            return new MovieInfo(in);
        }

        @Override
        public MovieInfo[] newArray(int size) {
            return new MovieInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(vote_count);
        dest.writeFloat(vote_average);
        dest.writeString(mtitle);
        dest.writeString(poster_path);
        dest.writeString(original_title);
        dest.writeString(backdrop_path);
        dest.writeString(mOverView);
        dest.writeString(mReleaseDate);

    }

    public int getVoteCount(){
        return vote_count;
    }

    public float getVote_average(){
        return vote_average;
    }

    public String getMtitle() {
        return mtitle;
    }

    public String getPoster_path(){
        return poster_path;
    }

    public String getOriginal_title(){
        return original_title;
    }
    public String getBackdrop_path() {
        return backdrop_path;
    }
    public String getmOverView() {
        return mOverView;
    }
    public String getmReleaseDate() {
        return mReleaseDate;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public void setmOverView(String mOverView) {
        this.mOverView = mOverView;
    }

    public void setmReleaseDate(String mReleaseDate) {
        this.mReleaseDate = mReleaseDate;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public void setMtitle(String mtitle) {
        this.mtitle = mtitle;
    }

    public void setVote_average(float vote_average) {
        this.vote_average = vote_average;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }
}
