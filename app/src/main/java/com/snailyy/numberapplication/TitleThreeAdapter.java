package com.snailyy.numberapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.snailyy.numberapplication.entity.MarketExpect;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.NonNull;

/**
 * @author yongjie created on 2020/4/22.
 */
public class TitleThreeAdapter extends RecyclerView.Adapter<CustomViewHolder> {
	private List<MarketExpect.WavePercent> titleEntityLIst = new ArrayList<>();
	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd HH:mm:ss");


	public void resetData(List<MarketExpect.WavePercent> dataLists) {
		titleEntityLIst.clear();
		titleEntityLIst.addAll(dataLists);
		notifyDataSetChanged();
	}

	@NonNull
	@Override
	public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_market_message, parent, false);
		return new CustomViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
		MarketExpect.WavePercent wavePercent = titleEntityLIst.get(position);
		TextView tvBuyWeight = (TextView) holder.getView(R.id.tv_buy_weight);
		TextView tvProfit = (TextView) holder.getView(R.id.tv_profit);
		TextView triggerPercent = (TextView) holder.getView(R.id.tv_trigger_percent);
		tvBuyWeight.setText("购买权重:" + NumberUtils.getPercentStr(wavePercent.getBuyWeight()));
		triggerPercent.setText("触发购买比例:" + NumberUtils.getPercentStr(wavePercent.getTriggerPercent()));
		float profitPercent = wavePercent.getProfitPercent();
		if (profitPercent > 0) {
			tvProfit.setText("盈利比例:"+NumberUtils.getPercentStr(profitPercent));
		} else {
			tvProfit.setText("盈利比例:" + "默认");
		}
	}

	@Override
	public int getItemCount() {
		return titleEntityLIst.size();
	}
}
