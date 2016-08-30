package com.example.arun.masterwork.detailviewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.arun.masterwork.R;

/**
 * Created by arun on 7/7/16.
 */

public class ReviewItem extends RecyclerView.ViewHolder {

    public TextView author = null;
    public TextView comments = null;

    public ReviewItem(View itemView) {
        super(itemView);
        author = (TextView) itemView.findViewById(R.id.author);
        comments = (TextView) itemView.findViewById(R.id.review);
    }
}
