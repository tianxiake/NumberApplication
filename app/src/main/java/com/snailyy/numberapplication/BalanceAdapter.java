package com.snailyy.numberapplication;

import android.text.TextUtils;
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
public class BalanceAdapter extends RecyclerView.Adapter<CustomViewHolder> {
	private List<AccountCurrencyEntity> titleEntityLIst = new ArrayList<>();
	private OnItemClickListener onItemClickListener;


	public OnItemClickListener getOnItemClickListener() {
		return onItemClickListener;
	}

	public void setOnItemClickListener(OnItemClickListener<AccountCurrencyEntity> onItemClickListener) {
		this.onItemClickListener = onItemClickListener;
	}

	public void resetData(List<AccountCurrencyEntity> dataLists) {
		titleEntityLIst.clear();
		titleEntityLIst.addAll(dataLists);
		notifyDataSetChanged();
	}

	@NonNull
	@Override
	public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.balance_recycler_item, parent, false);
		return new CustomViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
		AccountCurrencyEntity accountCurrencyEntity = titleEntityLIst.get(position);
		TextView titleText = (TextView) holder.getView(R.id.tv_title);
		TextView allText = (TextView) holder.getView(R.id.tv_all);
		TextView availableText = (TextView) holder.getView(R.id.tv_available);
		if (TextUtils.isEmpty(accountCurrencyEntity.getCurrency())) {
			titleText.setVisibility(View.GONE);
		} else {
			titleText.setVisibility(View.VISIBLE);
			titleText.setText(accountCurrencyEntity.getCurrency());
		}
		allText.setText("总共:" + NumberUtils.getScaleStr(accountCurrencyEntity.getBalance()));
		availableText.setText("可用:" + NumberUtils.getScaleStr(accountCurrencyEntity.getAvailable()));
		holder.itemView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (onItemClickListener != null) {
					onItemClickListener.onItemClick(accountCurrencyEntity, position);
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
