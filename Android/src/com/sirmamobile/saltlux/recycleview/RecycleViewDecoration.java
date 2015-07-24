package com.sirmamobile.saltlux.recycleview;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class RecycleViewDecoration extends DividerItemDecoration{

	public RecycleViewDecoration(Context context) {
		super(context, DividerItemDecoration.VERTICAL_LIST);
	}

	@Override
	public void drawVertical(Canvas c, RecyclerView parent) {
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left + params.leftMargin, top, right, bottom);
            mDivider.draw(c);
        }
    }
}
