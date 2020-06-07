package com.snailyy.numberapplication.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.snailyy.numberapplication.MessageCenter;
import com.snailyy.numberapplication.NumberUtils;
import com.snailyy.numberapplication.R;
import com.snailyy.numberapplication.TitleEntity;
import com.snailyy.numberapplication.TitleOneAdapter;
import com.snailyy.numberapplication.TitleRecyclerView;
import com.snailyy.numberapplication.entity.HealthTimeEntity;
import com.snailyy.numberapplication.entity.QueryResponseEntity;
import com.snailyy.numberapplication.entity.SurvivalEntity;
import com.snailyy.numberapplication.utils.HiLogger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HealthActivity extends AppCompatActivity {
	private static final String TAG = "HealthActivity";
	@BindView(R.id.rv_price_health)
	TitleRecyclerView rvPriceHealth;
	@BindView(R.id.rv_account_health)
	TitleRecyclerView rvAccountHealth;
	@BindView(R.id.rv_order_health)
	TitleRecyclerView rvOrderHealth;
	@BindView(R.id.rv_market_health)
	TitleRecyclerView rvMarketHealth;

	private TitleOneAdapter priceAdapter;
	private TitleOneAdapter accountAdapter;
	private TitleOneAdapter orderAdapter;
	private TitleOneAdapter marketAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_health);
		ButterKnife.bind(this);
		initView();
		showData();
	}

	private void showData() {
		QueryResponseEntity queryResponseEntity = MessageCenter.getInstance().getQueryResponseEntity();
		if (queryResponseEntity != null) {
			List<TitleEntity> priceHealth = buildData(queryResponseEntity, SurvivalEntity.TYPE_PRICE);
			HiLogger.d(TAG, "priceHealth:{}", priceHealth);
			if (priceHealth != null) {
				priceAdapter.resetData(priceHealth);
			}
			List<TitleEntity> orderHealth = buildData(queryResponseEntity, SurvivalEntity.TYPE_ORDER);
			HiLogger.d(TAG, "orderHealth:{}", orderHealth);
			if (orderHealth != null) {
				orderAdapter.resetData(orderHealth);
			}
			List<TitleEntity> accountHealth = buildData(queryResponseEntity, SurvivalEntity.TYPE_ACCOUNT);
			HiLogger.d(TAG, "accountHealth:{}", accountHealth);
			if (accountHealth != null) {
				accountAdapter.resetData(accountHealth);
			}
			List<TitleEntity> marketHealth = buildData(queryResponseEntity, SurvivalEntity.TYPE_MARKET_DEPTH);
			HiLogger.d(TAG, "marketHealth:{}", marketHealth);
			if (marketHealth != null) {
				marketAdapter.resetData(marketHealth);
			}
		}
	}

	private List<TitleEntity> buildData(QueryResponseEntity queryResponseEntity, String type) {
		List<SurvivalEntity> survivleEntities = queryResponseEntity.getSurvivleEntities();
		if (survivleEntities == null) {
			return null;
		}
		for (int i = 0; i < survivleEntities.size(); i++) {
			SurvivalEntity survivalEntity = survivleEntities.get(i);
			if (type.equals(survivalEntity.getType())) {
				List<TitleEntity> titleEntities = buildEntity(survivalEntity);
				return titleEntities;
			}
		}
		return null;
	}

	private List<TitleEntity> buildEntity(SurvivalEntity survivalEntity) {
		List<HealthTimeEntity> healthList = survivalEntity.getHealthList();
		List<TitleEntity> titleEntities = new ArrayList<>();
		for (int i = 0; i < healthList.size(); i++) {
			HealthTimeEntity healthTimeEntity = healthList.get(i);
			String title = healthTimeEntity.isHealth() ? "正常" : "异常";
			String date = NumberUtils.formatTime(healthTimeEntity.getTime());
			TitleEntity titleEntity = new TitleEntity(title, date);
			titleEntities.add(titleEntity);
		}
		return titleEntities;
	}


	private void initView() {
		priceAdapter = new TitleOneAdapter();
		accountAdapter = new TitleOneAdapter();
		orderAdapter = new TitleOneAdapter();
		marketAdapter = new TitleOneAdapter();

		rvPriceHealth.setAdapter(priceAdapter);
		rvAccountHealth.setAdapter(accountAdapter);
		rvOrderHealth.setAdapter(orderAdapter);
		rvMarketHealth.setAdapter(marketAdapter);

		rvPriceHealth.setTitle("price服务");
		rvAccountHealth.setTitle("account服务");
		rvOrderHealth.setTitle("order服务");
		rvMarketHealth.setTitle("market服务");
	}
}
