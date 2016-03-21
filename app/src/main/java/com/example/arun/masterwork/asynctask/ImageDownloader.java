package com.example.arun.masterwork.asynctask;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.arun.masterwork.DBHandler.DbManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

/**
 * Created by arun on 27/1/16.
 */
public class ImageDownloader extends AsyncTask<Bundle, Void, Integer> {

    private final String TAG = "ImageDownloaderTask";
    private Context mContext;
    private Random random = new Random();
    private File file = null;
    private DbManager dbManager = null;

    public ImageDownloader(Context context) {
        this.mContext = context;
    }

    @Override
    protected Integer doInBackground(Bundle... bundles) {
        int m=downloadAndSaveImageToInternal(bundles[0].getString("imageUrl"), bundles[0].getString("imageType"), bundles[0].getInt("movie_id"));
        return m;
    }

    public int downloadAndSaveImageToInternal(String imageUrl, String imageType, int movie_id) {
        try {
            Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(imageUrl).getContent());
            if (bitmap != null) {
                String fileName = Math.abs(random.nextInt()) + ".jpg";
                FileOutputStream fos = mContext.openFileOutput(fileName, Context.MODE_PRIVATE);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                fos.close();
                dbManager = new DbManager(mContext);
                int m=dbManager.insertImageLocalPathToDb(fileName, imageType, movie_id);
                return m;
            }
        } catch (MalformedURLException e) {
            Log.e(TAG, e.getMessage());
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
        return 0;
    }

    @Override
    protected void onPostExecute(Integer rowsUpdated) {
        if(rowsUpdated>0)
            Toast.makeText(mContext,"File successfully stored",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(mContext,"File not stored",Toast.LENGTH_SHORT).show();
    }
}
