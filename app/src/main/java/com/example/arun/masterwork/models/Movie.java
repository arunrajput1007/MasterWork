package com.example.arun.masterwork.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by arun on 19/12/15.
 */
public class Movie implements Parcelable {

    @SerializedName("id")
    private int id;
    @SerializedName("poster_path")
    private String imagePath;
    @SerializedName("title")
    private String title;
    @SerializedName("overview")
    private String description;
    @SerializedName("adult")
    private boolean adult;
    @SerializedName("release_date")
    private String releaseDate;
    @SerializedName("vote_average")
    private float rating;
    @SerializedName("vote_count")
    private int voteCount;
    @SerializedName("popularity")
    private float popularity;
    @SerializedName("backdrop_path")
    private String backImage;

    private String posterLocalPath;
    private String backImageLocalPath;

    public Movie() {
    }

    public Movie(Parcel in) {
        id = in.readInt();
        imagePath = in.readString();
        title = in.readString();
        description = in.readString();
        adult = (Boolean) in.readValue(null);
        releaseDate = in.readString();
        rating = in.readFloat();
        voteCount = in.readInt();
        popularity = in.readFloat();
        posterLocalPath = in.readString();
        backImage = in.readString();
        backImageLocalPath = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(imagePath);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeValue(adult);
        dest.writeString(releaseDate);
        dest.writeFloat(rating);
        dest.writeInt(voteCount);
        dest.writeFloat(popularity);
        dest.writeString(posterLocalPath);
        dest.writeString(backImage);
        dest.writeString(backImageLocalPath);
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public boolean isAdult() {
        return adult;
    }

    public float getRating() {
        return rating;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public float getPopularity() {
        return popularity;
    }

    public String getBackImage() {
        return backImage;
    }

    public String getPosterLocalPath() {
        return posterLocalPath;
    }

    public String getBackImageLocalPath() {
        return backImageLocalPath;
    }

    public void setPosterLocalPath(String posterLocalPath) {
        this.posterLocalPath = posterLocalPath;
    }

    public void setBackImageLocalPath(String backImageLocalPath) {
        this.backImageLocalPath = backImageLocalPath;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }

    public void setBackImage(String backImage) {
        this.backImage = backImage;
    }
}
