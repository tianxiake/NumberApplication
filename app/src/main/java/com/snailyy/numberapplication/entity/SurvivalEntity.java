package com.snailyy.numberapplication.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SurvivalEntity implements Serializable {
	public static final String TYPE_PRICE = "price";
	public static final String TYPE_ACCOUNT = "account";
	public static final String TYPE_MARKET_DEPTH = "marketDepth";
	public static final String TYPE_ORDER = "order";
	private String type;

	private List<HealthTimeEntity> healthList = new ArrayList<>();

	public SurvivalEntity() {
	}

	public SurvivalEntity(String type, List<HealthTimeEntity> healthList) {
		this.type = type;
		this.healthList = healthList;
	}

	public SurvivalEntity(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<HealthTimeEntity> getHealthList() {
		return healthList;
	}

	public void setHealthList(List<HealthTimeEntity> healthList) {
		this.healthList = healthList;
	}

	public void add(HealthTimeEntity healthTimeEntity) {
		healthList.add(healthTimeEntity);
	}

	public void clear() {
		healthList.clear();
	}


	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("SurvivalEntity{");
		sb.append("type='").append(type).append('\'');
		sb.append(", healthList=").append(healthList);
		sb.append('}');
		return sb.toString();
	}
}

