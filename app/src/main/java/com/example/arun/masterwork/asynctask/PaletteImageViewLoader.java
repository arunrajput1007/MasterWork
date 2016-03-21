package com.example.arun.masterwork.asynctask;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.arun.masterwork.adapter.Utility;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by arun on 13/3/16.
 */
public class PaletteImageViewLoader extends AsyncTask<String, Void, Bitmap> {

    private Context mContext = null;
    private ImageView backImage = null;
    private TextView titleLabel = null;
    private AppCompatActivity activity = null;

    public PaletteImageViewLoader(Context mContext,ImageView backImage,TextView titleLabel) {
        this.mContext = mContext;
        this.backImage = backImage;
        this.titleLabel = titleLabel;
        this.activity = (AppCompatActivity)mContext;
    }

    @Override
    protected void onPreExecute() {
        backImage.setImageBitmap(null);
    }

    @Override
    protected Bitmap doInBackground(String... imageUrl) {
        Bitmap bitmap = null;
        try {
            URL url = new URL(imageUrl[0]);
            bitmap = BitmapFactory.decodeStream(url.openStream());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        backImage.setImageBitmap(bitmap);
        backImage.setFitsSystemWindows(true);
        titleLabel.setBackgroundColor(Utility.getTitleBarColor(bitmap));
    }
}
