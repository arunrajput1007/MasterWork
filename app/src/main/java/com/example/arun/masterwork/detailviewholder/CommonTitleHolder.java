package com.example.arun.masterwork.detailviewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.arun.masterwork.R;

/**
 * Created by arun on 7/7/16.
 */

public class CommonTitleHolder extends RecyclerView.ViewHolder {

    public TextView commonTitle = null;

    public CommonTitleHolder(View view) {
        super(view);
        commonTitle = (TextView) view.findViewById(R.id.common_title);
    }
}
