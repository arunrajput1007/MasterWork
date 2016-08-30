package com.example.arun.masterwork.activity;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SyncStatusObserver;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.arun.masterwork.R;
import com.example.arun.masterwork.adapter.MainRecyclerAdapter;
import com.example.arun.masterwork.constants.MovieConstant;
import com.example.arun.masterwork.loaders.MovieLoader;
import com.example.arun.masterwork.provider.MovieProvider;
import com.example.arun.masterwork.provider.movie.MovieColumns;
import com.example.arun.masterwork.service.MovieNetworkService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    public static final String AUTHORITY = MovieProvider.AUTHORITY;
    public static final long SECONDS_PER_MINUTE = 60L;
    public static final long SYNC_INTERVAL_IN_MINUTES = 60L;
    public static final long SYNC_INTERVAL = 60L;
    private static final String ACCOUNT = "dummyaccount";
    private static final String ACCOUNT_TYPE = "example.com";
    static Account mAccount;
    @BindView(R.id.recycler_gridview)
    RecyclerView movieRecyclerView;
    private MainRecyclerAdapter adapter = null;
    private boolean favMenuButtonClick;
    private Unbinder unbinder = null;
    private SyncStatusObserver mSyncStatusObserver = new SyncStatusObserver() {
        /** Callback invoked with the sync adapter status changes. */
        @Override
        public void onStatusChanged(int which) {
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Account account = mAccount;
                    boolean syncActive = ContentResolver.isSyncActive(
                            account, AUTHORITY);
                    boolean syncPending = ContentResolver.isSyncPending(
                            account, AUTHORITY);
                    boolean refresh = syncActive || syncPending;
                    Bundle settingsBundle = new Bundle();
                    settingsBundle.putBoolean(
                            ContentResolver.SYNC_EXTRAS_MANUAL, true);
                    settingsBundle.putBoolean(
                            ContentResolver.SYNC_EXTRAS_EXPEDITED, true);
                    getContentResolver().startSync(MovieColumns.CONTENT_URI, settingsBundle);
                }
            });
        }
    };

    public static void createSyncAccount(Context context) {
        mAccount = new Account(ACCOUNT, ACCOUNT_TYPE);
        AccountManager accountManager = (AccountManager) context.getSystemService(ACCOUNT_SERVICE);
        Account[] accounts = accountManager.getAccounts();
        if (accountManager.addAccountExplicitly(mAccount, null, null)) {
            Log.e("AccountManager", "account is added");
            ContentResolver.setIsSyncable(mAccount, AUTHORITY, 1);
            ContentResolver.setSyncAutomatically(mAccount, AUTHORITY, true);
            ContentResolver.addPeriodicSync(mAccount, AUTHORITY, Bundle.EMPTY, SYNC_INTERVAL);
        } else {
            for (Account account : accounts) {
                if (mAccount.equals(account))
                    Log.e("AccountManager", "account already exists");
                else
                    Log.e("AccountManager", "Account manager error");
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        unbinder = ButterKnife.bind(this);

        checkIfFirstRun();
        createSyncAccount(this);

        movieRecyclerView = (RecyclerView) findViewById(R.id.recycler_gridview);
        movieRecyclerView.setHasFixedSize(true);
        GridLayoutManager manager = new GridLayoutManager(this, 2);
        movieRecyclerView.setLayoutManager(manager);
        adapter = new MainRecyclerAdapter(this, null);
        movieRecyclerView.setAdapter(adapter);
        getSupportLoaderManager().initLoader(0, null, this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSyncStatusObserver.onStatusChanged(0);
        final int mask = ContentResolver.SYNC_OBSERVER_TYPE_ACTIVE;
        ContentResolver.addStatusChangeListener(mask, mSyncStatusObserver);
    }

    private void checkIfFirstRun() {
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        if (!sharedPreferences.getBoolean("notfirst", false)) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("notfirst", true);
            editor.apply();
            Intent intent = new Intent(this, MovieNetworkService.class);
            intent.putExtra(MovieConstant.INTENT_DIFFERENTIATOR, MovieConstant.MOVIE_CATEGORY_POPULAR);
            startService(intent);
        }
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
                Intent intent = new Intent(this, MovieNetworkService.class);
                intent.putExtra(MovieConstant.INTENT_DIFFERENTIATOR, MovieConstant.MOVIE_CATEGORY_POPULAR);
                startService(intent);
                getSupportLoaderManager().restartLoader(1, null, this);
            }
        }
        if (id == R.id.menu_top_rated_movies) {
            if (item.isChecked())
                item.setChecked(false);
            else {
                item.setChecked(true);
                Intent intent = new Intent(this, MovieNetworkService.class);
                intent.putExtra(MovieConstant.INTENT_DIFFERENTIATOR, MovieConstant.MOVIE_CATEGORY_TOP_RATED);
                startService(intent);
                getSupportLoaderManager().restartLoader(1, null, this);
            }
        }
        if (id == R.id.menu_favorites) {
            if (item.isChecked())
                item.setChecked(false);
            else {
                item.setChecked(true);
                getSupportLoaderManager().restartLoader(1, null, new MovieLoader(this));
            }
        }
        if (id == R.id.menu_item_share) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "www.youtube.com/watch?v=" /*+ new Utility(this).getFirstTrailer()*/);
            sendIntent.setType("text/plain");
            startActivity(sendIntent);
        }
        return true;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        if (id == 0)
            return new CursorLoader(this, MovieColumns.CONTENT_URI, new String[]{MovieColumns._ID, MovieColumns.POSTER_PATH_LOCAL}
                    , MovieColumns.CATEGORY + "= ? ", new String[]{MovieConstant.MOVIE_CATEGORY_POPULAR}, null);
        if (id == 1)
            return new CursorLoader(this, MovieColumns.CONTENT_URI, new String[]{MovieColumns._ID, MovieColumns.POSTER_PATH_LOCAL}
                    , MovieColumns.CATEGORY + "= ? ", new String[]{MovieConstant.MOVIE_CATEGORY_TOP_RATED}, null);
        if (id == 2)
            return new CursorLoader(this, MovieColumns.CONTENT_URI, new String[]{MovieColumns._ID, MovieColumns.POSTER_PATH_LOCAL}
                    , MovieColumns.CATEGORY + "= ? ", new String[]{MovieConstant.MOVIE_CATEGORY_FAVORITE}, null);
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if (cursor != null) {
            adapter.swapCursor(cursor);
            getContentResolver().notifyChange(MovieColumns.CONTENT_URI, null, true);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
