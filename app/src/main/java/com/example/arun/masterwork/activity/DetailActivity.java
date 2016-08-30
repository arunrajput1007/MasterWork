package com.example.arun.masterwork.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.arun.masterwork.R;
import com.example.arun.masterwork.adapter.Utility;
import com.example.arun.masterwork.fragment.DetailFragment;
import com.example.arun.masterwork.provider.movie.MovieColumns;
import com.example.arun.masterwork.provider.movie.MovieCursor;
import com.example.arun.masterwork.provider.movie.MovieSelection;

public class DetailActivity extends AppCompatActivity {

    //@BindView(R.id.back_image)
    private ImageView backImage;
    //@BindView(R.id.title_label)
    private TextView titleLabelTv;
    //private Unbinder unbinder;
    private ShareActionProvider mShareActionProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //ButterKnife.bind(this);

        backImage = (ImageView) findViewById(R.id.back_image);
        titleLabelTv = (TextView) findViewById(R.id.title_label);

        int movieDbId = getIntent().getIntExtra("movie_db_id", 0);
        MovieSelection movieSelection = new MovieSelection();
        movieSelection.id(movieDbId);
        MovieCursor movieCursor = new MovieCursor(movieSelection.query(this.getContentResolver(), MovieColumns.ALL_COLUMNS));

        if (movieCursor.moveToFirst()) {
            Bitmap backBitmap = Utility.getImageBitmap(movieCursor.getBackdropPathLocal(), this);
            backImage.setImageBitmap(backBitmap);
            titleLabelTv.setText(movieCursor.getTitle());
            titleLabelTv.setBackgroundColor(Utility.getTitleBarColor(backBitmap));
        }

        DetailFragment fragment = new DetailFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("movie_db_id", movieDbId);
        fragment.setArguments(bundle);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.movie_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_item_share) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "www.youtube.com/watch?v=" /*+ Utility.getFirstTrailer()*/);
            sendIntent.setType("text/plain");
            startActivity(sendIntent);
        }
        return true;
    }

    /*@Override
    protected void onDestroy() {
        super.onDestroy();
        //unbinder.unbind();
    }*/
}
