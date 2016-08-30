package com.example.arun.masterwork.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.arun.masterwork.R;
import com.example.arun.masterwork.detailviewholder.CommonTitleHolder;
import com.example.arun.masterwork.detailviewholder.ReviewItem;
import com.example.arun.masterwork.detailviewholder.SynopsisHolder;
import com.example.arun.masterwork.detailviewholder.TrailerItem;
import com.example.arun.masterwork.provider.review.ReviewCursor;
import com.example.arun.masterwork.provider.trailer.TrailerCursor;

/**
 * Created by arun on 4/7/16.
 */

public class DetailRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int DETAIL_SYNOPSIS = 0;
    private final int DETAIL_COMMON_TITLE = 1;
    private final int DETAIL_COMMON_TRAILER_ITEM = 2;
    private final int DETAIL_COMMON_REVIEW_ITEM = 3;
    private Context mContext = null;
    private String description = null;
    private TrailerCursor trailerCursor = null;
    private int trailerMaxPosition = 0;
    private int reviewMaxPosition = 0;
    private int trailerTitlePosition = 0;
    private int reviewTitlePosition = 0;
    private ReviewCursor reviewCursor = null;
    private boolean trailer;
    private boolean review;

    public DetailRecyclerAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getItemCount() {
        return reviewMaxPosition + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return DETAIL_SYNOPSIS;
        if (position == 1)
            return DETAIL_COMMON_TITLE;
        if (position > 1 && position <= trailerMaxPosition)
            return DETAIL_COMMON_TRAILER_ITEM;
        if (position == trailerMaxPosition + 1)
            return DETAIL_COMMON_TITLE;
        if (position > trailerMaxPosition && position <= reviewMaxPosition)
            return DETAIL_COMMON_REVIEW_ITEM;
        else
            return -1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == DETAIL_SYNOPSIS) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.detail_synopsis, parent, false);
            return new SynopsisHolder(view);
        }
        if (viewType == DETAIL_COMMON_TITLE) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.list_header, parent, false);
            return new CommonTitleHolder(view);
        }
        if (viewType == DETAIL_COMMON_TRAILER_ITEM) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.trailer_row, parent, false);
            return new TrailerItem(view);
        }
        if (viewType == DETAIL_COMMON_REVIEW_ITEM) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.review_row, parent, false);
            return new ReviewItem(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == DETAIL_SYNOPSIS) {
            SynopsisHolder synopsisHolder = (SynopsisHolder) holder;
            synopsisHolder.descriptionText.setText(description);
        }
        if (getItemViewType(position) == DETAIL_COMMON_TITLE && position < trailerMaxPosition) {
            trailerTitlePosition = position;
            CommonTitleHolder commonTitleHolder = (CommonTitleHolder) holder;
            commonTitleHolder.commonTitle.setText("Trailer");
        }
        if (getItemViewType(position) == DETAIL_COMMON_TRAILER_ITEM && trailerCursor != null) {
            TrailerItem trailerItem = (TrailerItem) holder;
            trailerItem.trailerName.setText(trailerCursor.getName());
            trailerCursor.moveToPosition(position - trailerTitlePosition - 1);
        }
        if (getItemViewType(position) == DETAIL_COMMON_TITLE && position > trailerMaxPosition) {
            reviewTitlePosition = position;
            CommonTitleHolder commonTitleHolder = (CommonTitleHolder) holder;
            commonTitleHolder.commonTitle.setText("Review");
        }
        if (getItemViewType(position) == DETAIL_COMMON_REVIEW_ITEM && reviewCursor != null) {
            ReviewItem reviewItem = (ReviewItem) holder;
            reviewItem.author.setText(reviewCursor.getAuthor());
            reviewItem.comments.setText(reviewCursor.getComments());
            reviewCursor.moveToPosition(position - reviewTitlePosition - 1);
        }
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTrailerCursor(TrailerCursor trailerCursor) {
        this.trailerCursor = trailerCursor;
        trailer = true;
        checkBothCursorUpdate(true, review);
    }

    public void setReviewCursor(ReviewCursor reviewCursor) {
        this.reviewCursor = reviewCursor;
        review = true;
        checkBothCursorUpdate(trailer, true);
    }

    public void checkBothCursorUpdate(boolean trailer, boolean review) {
        if (trailer && review) {
            trailerMaxPosition = 1 + trailerCursor.getCount();
            reviewMaxPosition = trailerMaxPosition + reviewCursor.getCount() + 1;
            notifyDataSetChanged();
        }
    }
}
