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
 * @author yongjie created on 2020/4/22.
 */
public class TitleOneAdapter extends RecyclerView.Adapter<CustomViewHolder> {
	private List<TitleEntity> titleEntityLIst = new ArrayList<>();
	private OnItemClickListener onItemClickListener;


	public OnItemClickListener getOnItemClickListener() {
		return onItemClickListener;
	}

	public void setOnItemClickListener(OnItemClickListener<TitleEntity> onItemClickListener) {
		this.onItemClickListener = onItemClickListener;
	}

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
		if (TextUtils.isEmpty(titleEntity.getTitle())) {
			titleTextView.setVisibility(View.GONE);
		} else {
			titleTextView.setVisibility(View.VISIBLE);
			titleTextView.setText(titleEntity.getTitle());
		}
		messageTextView.setText(titleEntity.getMessage());
		holder.itemView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (onItemClickListener != null) {
					onItemClickListener.onItemClick(titleEntity, position);
				}
			}
		});
	}

	@Override
	public int getItemCount() {
		return titleEntityLIst.size();
	}

	public interface OnItemClickListener<T> {
		void onItemClick(T data, int position);
	}
}
