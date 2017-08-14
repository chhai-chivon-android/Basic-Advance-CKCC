package com.nuon.peter.demoapp.utils.views.recyclerview;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by beniten on 7/21/16.
 */
public class SpacesItemDecoration extends RecyclerView.ItemDecoration {

  private int halfSpace;

  public SpacesItemDecoration(int space) {
    this.halfSpace = space / 2;
  }

  @Override
  public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

    if (parent.getPaddingLeft() != halfSpace) {
      parent.setPadding(halfSpace, halfSpace, halfSpace, halfSpace);
      parent.setClipToPadding(false);
    }

    outRect.top = halfSpace;
    outRect.bottom = halfSpace;
    outRect.left = halfSpace;
    outRect.right = halfSpace;
  }
}
