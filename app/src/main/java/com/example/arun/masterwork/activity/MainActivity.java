package com.example.arun.masterwork.activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.arun.masterwork.R;
import com.example.arun.masterwork.adapter.MovieGridAdapter;
import com.example.arun.masterwork.adapter.Utility;
import com.example.arun.masterwork.constants.MOVIECONSTANT;
import com.example.arun.masterwork.fragment.DetailFragment;
import com.example.arun.masterwork.models.Movie;
import com.example.arun.masterwork.network.NetworkManager;
import com.example.arun.masterwork.provider.movie.MovieColumns;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    @Bind(R.id.gridview)
    GridView movieGridView;
    private NetworkManager networkManager = null;
    private MovieGridAdapter movieGridAdapter = null;
    private boolean mTwoPane;
    private boolean favMenuButtonClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        movieGridAdapter = new MovieGridAdapter(this);
        movieGridView.setAdapter(movieGridAdapter);
        networkManager = new NetworkManager(this);

        //By Default Popular Movies are fetched and showed in GridAdapter
        networkManager.fetchMovies(MOVIECONSTANT.POPULAR_MOVIES_URL,movieGridAdapter);

        if(findViewById(R.id.movie_detail_container)!=null){
            mTwoPane = true;
        }

        movieGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Movie movie = (Movie) parent.getAdapter().getItem(position);
                if (mTwoPane) {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("movie",movie);
                    DetailFragment fragment = new DetailFragment();
                    fragment.setArguments(bundle);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.movie_detail_container, fragment)
                            .commit();
                } else {
                    Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                    intent.putExtra("movie",movie);
                    startActivity(intent);
                }
            }
        });
        getSupportLoaderManager().initLoader(0,null,this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_pop_movies) {
            if (item.isChecked())
                item.setChecked(false);
            else {
                item.setChecked(true);
                networkManager.fetchMovies(MOVIECONSTANT.POPULAR_MOVIES_URL, movieGridAdapter);
            }
        }
        if (id == R.id.menu_top_rated_movies) {
            if (item.isChecked())
                item.setChecked(false);
            else {
                item.setChecked(true);
                networkManager.fetchMovies(MOVIECONSTANT.TOP_RATED_MOVIES_URL, movieGridAdapter);
            }
        }
        if (id == R.id.menu_favorites) {
            if (item.isChecked())
                item.setChecked(false);
            else {
                item.setChecked(true);
                getSupportLoaderManager().restartLoader(1,null,this);
            }
        }
        if(id==R.id.menu_item_share){
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "www.youtube.com/watch?v="+ Utility.getFirstTrailer());
            sendIntent.setType("text/plain");
            startActivity(sendIntent);
        }
        return true;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        if(id==1)
            favMenuButtonClick =true;
        return new CursorLoader(this,MovieColumns.CONTENT_URI,null,null,null,null);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if(favMenuButtonClick){
                Utility utility = new Utility();
                ArrayList<Movie> movieList=utility.getMovieListFromCursor(cursor);
                movieGridAdapter.updateData(movieList);
                favMenuButtonClick=false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

}
