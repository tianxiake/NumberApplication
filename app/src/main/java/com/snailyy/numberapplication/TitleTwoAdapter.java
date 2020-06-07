package com.snailyy.numberapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.snailyy.numberapplication.entity.TradeEntity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.NonNull;

/**
 * @author yongjie created on 2020/4/22.
 */
public class TitleTwoAdapter extends RecyclerView.Adapter<CustomViewHolder> {
	private List<TradeEntity> titleEntityLIst = new ArrayList<>();
	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
	private OnItemClickListener<TradeEntity> onItemClickListener;


	public void resetData(List<TradeEntity> dataLists) {
		titleEntityLIst.clear();
		titleEntityLIst.addAll(dataLists);
		notifyDataSetChanged();
	}

	public void setOnItemClickListener(OnItemClickListener<TradeEntity> onItemClickListener) {
		this.onItemClickListener = onItemClickListener;
	}

	@NonNull
	@Override
	public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_trade_message, parent, false);
		return new CustomViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
		TradeEntity tradeEntity = titleEntityLIst.get(position);
		TextView priceText = (TextView) holder.getView(R.id.tv_trade_price);
		TextView volumeText = (TextView) holder.getView(R.id.tv_trade_volume);
		TextView tradeTimeText = (TextView) holder.getView(R.id.tv_trade_time);
		TextView profitText = (TextView) holder.getView(R.id.tv_trade_profitPercent);
		TextView orderId = (TextView) holder.getView(R.id.tv_order_id);
		priceText.setText("购买价:" + tradeEntity.getTradePrice());
		volumeText.setText("购买数量:" + NumberUtils.getScaleStr(tradeEntity.getTradeVolume()));
		tradeTimeText.setText("交易时间:" + simpleDateFormat.format(tradeEntity.getTradeTime()));
		float profitPercent = tradeEntity.getProfitPercent();
		if (profitPercent > 0) {
			profitText.setText("盈利比例:" + NumberUtils.getPercentStr(profitPercent));
		} else {
			profitText.setText("盈利比例:默认");
		}
		orderId.setText("订单号:" + tradeEntity.getTradeId());
		holder.itemView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (onItemClickListener != null) {
					onItemClickListener.onItemClick(tradeEntity, position);
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

