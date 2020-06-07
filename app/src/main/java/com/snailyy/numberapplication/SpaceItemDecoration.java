package com.snailyy.numberapplication;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by chances on 16/6/23.
 */
public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
	private int leftSpace;
	private int topSpace;
	private int rightSpace;
	private int bottomSpace;
	private boolean showSpaceInEdge = true;

	public SpaceItemDecoration(int leftSpace, int topSpace, int rightSpace, int bottomSpace) {
		this.leftSpace = leftSpace;
		this.topSpace = topSpace;
		this.rightSpace = rightSpace;
		this.bottomSpace = bottomSpace;
	}

	public SpaceItemDecoration(int leftSpace, int topSpace, int rightSpace, int bottomSpace, boolean showSpaceInEdge) {
		this.leftSpace = leftSpace;
		this.topSpace = topSpace;
		this.rightSpace = rightSpace;
		this.bottomSpace = bottomSpace;
		this.showSpaceInEdge = showSpaceInEdge;
	}

	public boolean isShowSpaceInEdge() {
		return showSpaceInEdge;
	}

	public void setShowSpaceInEdge(boolean showSpaceInEdge) {
		this.showSpaceInEdge = showSpaceInEdge;
	}

	@Override
	public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
		super.getItemOffsets(outRect, view, parent, state);
		outRect.bottom = bottomSpace;
		outRect.left = leftSpace;
		outRect.right = rightSpace;
		outRect.top = topSpace;
		if (!showSpaceInEdge) {
			RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
			if (layoutManager instanceof GridLayoutManager) {
				int childAdapterPosition = parent.getChildAdapterPosition(view);
				GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
				int orientation = gridLayoutManager.getOrientation();
				if (orientation == LinearLayoutManager.VERTICAL) {
					int spanSize = gridLayoutManager.getSpanSizeLookup().getSpanSize(childAdapterPosition);
					if (spanSize == gridLayoutManager.getSpanCount()) {
						outRect.right = 0;
						outRect.left = 0;
					} else {
						int spanIndex = gridLayoutManager.getSpanSizeLookup().getSpanIndex(childAdapterPosition, gridLayoutManager.getSpanCount());
						if (spanIndex == 0) {
							outRect.left = 0;
						} else if (spanIndex == gridLayoutManager.getSpanCount() - 1) {
							outRect.right = 0;
						}
					}
				}
			} else if (layoutManager instanceof LinearLayoutManager) {

			}
		}

	}
}