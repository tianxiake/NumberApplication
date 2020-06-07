package com.snailyy.numberapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.snailyy.numberapplication.entity.AccountCurrencyEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yongjie created on 2020/5/29.
 */
public class ServiceStateAdapter extends RecyclerView.Adapter<CustomViewHolder> {

	private List<ServiceTitleEntity> titleEntityLIst = new ArrayList<>();
	private OnItemClickListener onItemClickListener;

	public OnItemClickListener getOnItemClickListener() {
		return onItemClickListener;
	}

	public void setOnItemClickListener(OnItemClickListener<AccountCurrencyEntity> onItemClickListener) {
		this.onItemClickListener = onItemClickListener;
	}

	public void resetData(List<ServiceTitleEntity> dataLists) {
		titleEntityLIst.clear();
		titleEntityLIst.addAll(dataLists);
		notifyDataSetChanged();
	}

	@NonNull
	@Override
	public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.common_recycler_item, parent, false);
		return new CustomViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
		ServiceTitleEntity titleEntity = titleEntityLIst.get(position);
		TextView titleText = (TextView) holder.getView(R.id.tv_title);
		titleText.setTextColor(holder.itemView.getResources().getColor(R.color.base_default_color));
		TextView messageText = (TextView) holder.getView(R.id.tv_message);
		messageText.setTextColor(holder.itemView.getResources().getColor(R.color.base_default_color));
		String type = titleEntity.getType();

		titleText.setText(titleEntity.getTitle());
		messageText.setText(titleEntity.getMessage());
		if (ServiceTitleEntity.TYPE_WARN.equals(type)) {
			if (titleEntity.isWarn) {
				messageText.setTextColor(holder.itemView.getResources().getColor(R.color.warn_color));
			}
		}
	}

	@Override
	public int getItemCount() {
		return titleEntityLIst.size();
	}

	public interface OnItemClickListener<T> {
		void onItemClick(T data, int position);
	}
}
