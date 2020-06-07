package com.snailyy.numberapplication;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yongjie created on 2020/4/25.
 */
public class MyAdapter extends RecyclerView.Adapter<CustomViewHolder> implements AdapterInterface<TitleEntity> {

	private List<TitleEntity> titleEntityLIst = new ArrayList<>();

	@Override
	public void resetData(List<TitleEntity> dataLists) {
		titleEntityLIst.clear();
		titleEntityLIst.addAll(dataLists);
		notifyDataSetChanged();
	}

	@NonNull
	@Override
	public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_simple_message, parent, false);
		return new CustomViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
		TitleEntity titleEntity = titleEntityLIst.get(position);
		TextView titleTextView = (TextView) holder.getView(R.id.tv_title);
		TextView messageTextView = (TextView) holder.getView(R.id.tv_message);
		titleTextView.setVisibility(View.VISIBLE);
		titleTextView.setText(titleEntity.getTitle());
		if (TextUtils.isEmpty(titleEntity.getTitle())) {
			titleTextView.setVisibility(View.INVISIBLE);
		}
		messageTextView.setText(titleEntity.getMessage());
	}

	@Override
	public int getItemCount() {
		return titleEntityLIst.size();
	}


}
