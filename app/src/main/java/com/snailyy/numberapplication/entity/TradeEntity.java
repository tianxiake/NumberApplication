package com.snailyy.numberapplication.entity;

import java.io.Serializable;

public class TradeEntity implements Serializable {
	/**
	 * 成交订单号
	 */
	private long tradeId;
	/**
	 * 成交价
	 */
	private float tradePrice;
	/**
	 * 成交量
	 */
	private float tradeVolume;

	/**
	 * 盈利比例 如果是-1就用全局配置的盈利比例 这个值取值[0,1]
	 */
	private float profitPercent = -1f;
	/**
	 * 交易时间
	 */
	private long tradeTime;

	public long getTradeId() {
		return tradeId;
	}

	public void setTradeId(long tradeId) {
		this.tradeId = tradeId;
	}

	public float getTradePrice() {
		return tradePrice;
	}

	public void setTradePrice(float tradePrice) {
		this.tradePrice = tradePrice;
	}

	public float getTradeVolume() {
		return tradeVolume;
	}

	public void setTradeVolume(float tradeVolume) {
		this.tradeVolume = tradeVolume;
	}

	public long getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(long tradeTime) {
		this.tradeTime = tradeTime;
	}

	public float getProfitPercent() {
		return profitPercent;
	}

	public void setProfitPercent(float profitPercent) {
		this.profitPercent = profitPercent;
	}

	public TradeEntity copy() {
		TradeEntity tradeEntity = new TradeEntity();
		tradeEntity.tradeId = this.tradeId;
		tradeEntity.tradePrice = this.tradePrice;
		tradeEntity.tradeVolume = this.tradeVolume;
		tradeEntity.profitPercent = this.profitPercent;
		tradeEntity.tradeTime = this.tradeTime;
		return tradeEntity;
	}


	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("TradeEntity{");
		sb.append("tradeId=").append(tradeId);
		sb.append(", tradePrice=").append(tradePrice);
		sb.append(", tradeVolume=").append(tradeVolume);
		sb.append(", profitPercent=").append(profitPercent);
		sb.append(", tradeTime=").append(tradeTime);
		sb.append('}');
		return sb.toString();
	}
}
