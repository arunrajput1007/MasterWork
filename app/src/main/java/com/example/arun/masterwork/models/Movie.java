package com.example.arun.masterwork.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by arun on 29/6/16.
 */
public class Movie implements Parcelable, Databasable {

    static final Creator<Movie> CREATOR = new Creator<Movie>() {

        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

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
    private String category;
    private String posterLocalPath;
    private String backImageLocalPath;

    public Movie() {
    }

    private Movie(Parcel in) {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public float getPopularity() {
        return popularity;
    }

    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }

    public String getBackImage() {
        return backImage;
    }

    public void setBackImage(String backImage) {
        this.backImage = backImage;
    }

    public String getPosterLocalPath() {
        return posterLocalPath;
    }

    public void setPosterLocalPath(String posterLocalPath) {
        this.posterLocalPath = posterLocalPath;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBackImageLocalPath() {
        return backImageLocalPath;
    }

    public void setBackImageLocalPath(String backImageLocalPath) {
        this.backImageLocalPath = backImageLocalPath;
    }
}
