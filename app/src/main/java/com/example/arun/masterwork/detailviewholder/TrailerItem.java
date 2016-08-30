package com.example.arun.masterwork.detailviewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.arun.masterwork.R;

/**
 * Created by arun on 7/7/16.
 */

public class TrailerItem extends RecyclerView.ViewHolder {

    public TextView trailerName = null;

    public TrailerItem(View itemView) {
        super(itemView);
        trailerName = (TextView) itemView.findViewById(R.id.trailer_name_tv);
    }
}
