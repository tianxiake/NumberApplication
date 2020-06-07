package com.snailyy.numberapplication.activity;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.snailyy.numberapplication.DisplayUtil;
import com.snailyy.numberapplication.MessageCenter;
import com.snailyy.numberapplication.NumberUtils;
import com.snailyy.numberapplication.R;
import com.snailyy.numberapplication.TitleRecyclerView;
import com.snailyy.numberapplication.TitleThreeAdapter;
import com.snailyy.numberapplication.entity.MarketExpect;
import com.snailyy.numberapplication.entity.QueryResponseEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MarketExpectActivity extends AppCompatActivity {

	@BindView(R.id.tv_current_market)
	TextView tvCurrentMarket;
	@BindView(R.id.tv_default_profit)
	TextView tvDefaultProfit;
	@BindView(R.id.ll_container)
	LinearLayout llContainer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_market);
		ButterKnife.bind(this);
		showData();
	}

	private void showData() {
		QueryResponseEntity queryResponseEntity = MessageCenter.getInstance().getQueryResponseEntity();
		if (queryResponseEntity != null) {
			tvCurrentMarket.setText("当前市场预期：" + queryResponseEntity.getCurrentMarketExpects());
			float defaultProfitPercent = queryResponseEntity.getDefaultProfitPercent();
			tvDefaultProfit.setText("默认盈利比例：" + NumberUtils.getPercentStr(defaultProfitPercent));
			List<MarketExpect> marketExpects = queryResponseEntity.getMarketExpects();
			for (int i = 0; i < marketExpects.size(); i++) {
				MarketExpect marketExpect = marketExpects.get(i);
				TitleRecyclerView titleRecyclerView = buildTitleRecyclerView();
				TitleThreeAdapter titleThreeAdapter = new TitleThreeAdapter();
				titleRecyclerView.setAdapter(titleThreeAdapter);
				titleRecyclerView.setTitle(marketExpect.getMarketExpect() + "/firstBuy:" + NumberUtils.getPercentStr(marketExpect.getFirstBuyWeight()));
				titleThreeAdapter.resetData(marketExpect.getWave());
			}
		}
	}

	private TitleRecyclerView buildTitleRecyclerView() {
		TitleRecyclerView titleRecyclerView = new TitleRecyclerView(this);
		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		layoutParams.setMargins(0, DisplayUtil.dip2px(this, 5), 0, 0);
		titleRecyclerView.setLayoutParams(layoutParams);
		llContainer.addView(titleRecyclerView);
		return titleRecyclerView;
	}

}
