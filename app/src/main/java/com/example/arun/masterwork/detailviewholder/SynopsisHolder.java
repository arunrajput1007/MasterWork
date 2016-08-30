package com.example.arun.masterwork.detailviewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.arun.masterwork.R;

/**
 * Created by arun on 4/7/16.
 */

public class SynopsisHolder extends RecyclerView.ViewHolder {

    public TextView descriptionText = null;

    public SynopsisHolder(View view) {
        super(view);
        descriptionText = (TextView) view.findViewById(R.id.synopsis_description);
    }
}
