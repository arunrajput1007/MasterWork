package com.example.arun.masterwork.fragment;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.arun.masterwork.DBHandler.DbManager;
import com.example.arun.masterwork.R;
import com.example.arun.masterwork.adapter.Utility;
import com.example.arun.masterwork.asynctask.PaletteImageViewLoader;
import com.example.arun.masterwork.constants.IMAGECONSTANT;
import com.example.arun.masterwork.constants.MOVIECONSTANT;
import com.example.arun.masterwork.models.Movie;
import com.example.arun.masterwork.network.NetworkManager;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailFragment extends Fragment {

    @Bind(R.id.poster_image)
    ImageView posterImage;
    @Bind(R.id.release_date_tv)
    TextView releaseDate;
    @Bind(R.id.rating_tv)
    TextView rating;
    @Bind(R.id.synopsis)
    TextView synopsis;
    @Bind(R.id.tablet_detail)
    LinearLayout tabletLinearLayout;

    private FloatingActionButton favButton = null;
    private ImageView backImage = null;
    private TextView title_label = null;

    private String TAG = "DetailFragment";
    private Context mContext = null;
    private Activity activity = null;
    private Movie movie = null;
    private NetworkManager networkManager = null;
    private boolean mTwoPane;

    public DetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments().containsKey("movie")){
            movie = (Movie) getArguments().get("movie");
            activity = this.getActivity();
            mContext = activity;

            networkManager = new NetworkManager(activity);
            networkManager.fetchMovieReviews(movie.getId());
            networkManager.fetchMovieTrailer(movie.getId());
        }

        if(activity.findViewById(R.id.collapsing_toolbar)==null)
            mTwoPane=true;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.movie_detail, container, false);
        ButterKnife.bind(this, view);

        Picasso.with(mContext).load(IMAGECONSTANT.IMAGE_BASE_URL_POSTER_IMAGE + movie.getImagePath()).into(posterImage);
        releaseDate.setText("Release Date :" + movie.getReleaseDate());
        rating.setText("Ratings :" + movie.getRating());
        synopsis.setText(movie.getDescription());
        favButton = (FloatingActionButton) activity.findViewById(R.id.fab);

        if(!mTwoPane){
            backImage = (ImageView) activity.findViewById(R.id.back_image);
            title_label = (TextView)activity.findViewById(R.id.title_label);
            tabletLinearLayout.setVisibility(View.GONE);
        }
        else{
            backImage = (ImageView) view.findViewById(R.id.back_image_tablet);
            title_label = (TextView) view.findViewById(R.id.title_label_tablet);
        }

        if(movie.getBackImageLocalPath()!=null){
            Utility utility = new Utility(mContext);
            Bitmap b= utility.getImageBitmap(movie.getBackImageLocalPath());
            backImage.setImageBitmap(b);
            posterImage.setImageBitmap(utility.getImageBitmap(movie.getPosterLocalPath()));
            title_label.setBackgroundColor(Utility.getTitleBarColor(b));
        }
        else
        new PaletteImageViewLoader(mContext,backImage,title_label).execute(IMAGECONSTANT.IMAGE_BASE_URL_BACK_IMAGE+movie.getBackImage());

        title_label.setText(movie.getTitle());

        favButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbManager dbManager = new DbManager(mContext);
                int i=dbManager.addMovieToDb(movie, MOVIECONSTANT.MOVIE_CATEGORY_FAVORITE_DB);
                if(i==-1)
                    Toast.makeText(mContext,"already exist",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(mContext,"inserted into db :"+i,Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
