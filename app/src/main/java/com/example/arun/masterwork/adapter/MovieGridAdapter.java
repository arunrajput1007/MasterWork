package com.example.arun.masterwork.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.arun.masterwork.constants.IMAGECONSTANT;
import com.example.arun.masterwork.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by arun on 15/3/16.
 */
public class MovieGridAdapter extends BaseAdapter {

    private Context mContext = null;
    private ArrayList<Movie> movieList = new ArrayList<>();

    public MovieGridAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void updateData(ArrayList<Movie> movieList){
        this.movieList.clear();
        this.movieList=movieList;
        notifyDataSetChanged();
    }

    public int getCount() {
        return movieList.size();
    }

    public Movie getItem(int position) {
        return movieList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView posterImage = null;
        if (convertView == null) {
            posterImage = new ImageView(mContext);
        } else {
            posterImage = (ImageView) convertView;
        }
        Utility utility = new Utility(mContext);
        if(movieList.get(position).getPosterLocalPath()!=null){
            Bitmap b=utility.getImageBitmap(movieList.get(position).getPosterLocalPath());
            posterImage.setImageBitmap(b);
            posterImage.setAdjustViewBounds(true);
            return posterImage;
        }
        if(movieList.size()!=0) {
            Picasso.with(mContext).load(IMAGECONSTANT.IMAGE_BASE_URL_POSTER_IMAGE + movieList.get(position).getImagePath()).into(posterImage);
            posterImage.setAdjustViewBounds(true);
        }
        return posterImage;
    }


}
