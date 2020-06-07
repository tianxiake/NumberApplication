package com.snailyy.numberapplication.activity;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.snailyy.numberapplication.DisplayUtil;
import com.snailyy.numberapplication.MessageCenter;
import com.snailyy.numberapplication.NumberUtils;
import com.snailyy.numberapplication.R;
import com.snailyy.numberapplication.TitleEntity;
import com.snailyy.numberapplication.TitleOneAdapter;
import com.snailyy.numberapplication.TitleRecyclerView;
import com.snailyy.numberapplication.entity.HealthTimeEntity;
import com.snailyy.numberapplication.entity.QueryResponseEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VdsMessageActivity extends AppCompatActivity {

	@BindView(R.id.fl_container)
	LinearLayout flContainer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vds_message);
		ButterKnife.bind(this);
		showData();
	}

	private void showData() {
		QueryResponseEntity queryResponseEntity = MessageCenter.getInstance().getQueryResponseEntity();
		if (queryResponseEntity != null) {
			List<HealthTimeEntity> healthTimeEntities = queryResponseEntity.getVdsServicesHealthTimes();
			if (healthTimeEntities != null) {
				TitleRecyclerView titleRecyclerView = buildTitleRecyclerView();
				TitleOneAdapter titleOneAdapter = new TitleOneAdapter();
				titleRecyclerView.setAdapter(titleOneAdapter);
				titleRecyclerView.setTitle("服务存活时间");
				List<TitleEntity> dataLists = buildTitleEntity(healthTimeEntities);
				titleOneAdapter.resetData(dataLists);
			}
		}
	}

	private List<TitleEntity> buildTitleEntity(List<HealthTimeEntity> healthList) {
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

	private TitleRecyclerView buildTitleRecyclerView() {
		TitleRecyclerView titleRecyclerView = new TitleRecyclerView(this);
		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		layoutParams.setMargins(0, DisplayUtil.dip2px(this, 5), 0, 0);
		titleRecyclerView.setLayoutParams(layoutParams);
		flContainer.addView(titleRecyclerView);
		return titleRecyclerView;
	}
}
