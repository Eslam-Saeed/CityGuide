package com.mti.cityguide.helpers;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by Eslam on 03/22/2019.
 */

public class SpacingItemDecoration extends RecyclerView.ItemDecoration {
    private int spacing;
    private Context context;
    private boolean isTop;

    public SpacingItemDecoration(Context context, int padding) {
        this.context = context;
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        spacing = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, padding, metrics);
    }

    public SpacingItemDecoration(Context context, int padding, boolean isTop) {
        this.context = context;
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        spacing = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, padding, metrics);
        this.isTop = isTop;
    }

    @Override
    public final void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);
        if (position != 0) {
            if (!isTop) {
                outRect.left = spacing;
                outRect.right = spacing;
            } else {
                outRect.top = spacing;
                outRect.bottom = spacing;
            }

        }
    }
}