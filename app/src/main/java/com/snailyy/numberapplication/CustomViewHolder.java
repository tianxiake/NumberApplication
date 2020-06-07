package com.snailyy.numberapplication;

import android.util.SparseArray;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author yongjie created on 2019-07-23.
 */
public class CustomViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> sparseArray = new SparseArray();

    public CustomViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    /**
     * 根据Id去查询ViewView
     *
     * @param viewId
     * @return
     */
    public View getView(int viewId) {
        View view = sparseArray.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            sparseArray.put(viewId, view);
        }
        return view;
    }
}
