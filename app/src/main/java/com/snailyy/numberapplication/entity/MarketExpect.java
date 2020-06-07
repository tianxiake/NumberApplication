package com.snailyy.numberapplication.entity;

import com.snailyy.numberapplication.utils.HiLogger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MarketExpect implements Serializable {
	public static final String TAG = "MarketExpect";
	/**
	 * 市场预测 看涨、看跌、极度看跌
	 */
	private String marketExpect;

	/**
	 * 首次购买占总总余额的权重 取值[0,1] 如果是0.35 表示首次购买占总可用余额的35% [0-1]
	 * 这个参数只有 marketExpect 是看涨的情况下才会进行使用
	 */
	private float firstBuyWeight;

	/**
	 * 波动购买比例集合 比如首次触发比例是0.035 这个集合里面如果有0.05 则购买的比例是多少
	 */
	private List<WavePercent> wave = new ArrayList<>();

	public String getMarketExpect() {
		return marketExpect;
	}

	public void setMarketExpect(String marketExpect) {
		this.marketExpect = marketExpect;
	}

	public float getFirstBuyWeight() {
		return firstBuyWeight;
	}

	public void setFirstBuyWeight(float firstBuyWeight) {
		this.firstBuyWeight = firstBuyWeight;
	}

	public List<WavePercent> getWave() {
		return wave;
	}

	public synchronized void setWave(List<WavePercent> wave) {
		List<WavePercent> tempWave = new ArrayList<>();
		//wave按照从小到大进行排序 比如 -5 -4 -3 0 1
		Collections.sort(wave, new Comparator<WavePercent>() {
			@Override
			public int compare(WavePercent o1, WavePercent o2) {
				if (o1.triggerPercent > o2.triggerPercent) {
					return 1;
				} else if (o1.triggerPercent == o2.triggerPercent) {
					tempWave.add(o1);
					return 1;
				}
				return -1;
			}
		});
		for (int i = 0; i < tempWave.size(); i++) {
			wave.remove(tempWave.get(i));
		}
		HiLogger.d(TAG, "setWave==>{}", wave);
		this.wave = wave;
	}

	public static class WavePercent {
		/**
		 * 取值[-1,1]
		 */
		private float triggerPercent;
		/**
		 * [0-1]
		 */
		private float buyWeight;

		/**
		 * 盈利比例
		 */
		private float profitPercent = -1f;

		public float getTriggerPercent() {
			return triggerPercent;
		}

		public void setTriggerPercent(float triggerPercent) {
			this.triggerPercent = triggerPercent;
		}

		public float getBuyWeight() {
			return buyWeight;
		}

		public void setBuyWeight(float buyWeight) {
			this.buyWeight = buyWeight;
		}

		public float getProfitPercent() {
			return profitPercent;
		}

		public void setProfitPercent(float profitPercent) {
			this.profitPercent = profitPercent;
		}

		@Override
		public String toString() {
			final StringBuilder sb = new StringBuilder("WavePercent{");
			sb.append("triggerPercent=").append(triggerPercent);
			sb.append(", buyWeight=").append(buyWeight);
			sb.append(", profitPercent=").append(profitPercent);
			sb.append('}');
			return sb.toString();
		}
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("MarketExpect{");
		sb.append("marketExpect='").append(marketExpect).append('\'');
		sb.append(", firstBuyWeight=").append(firstBuyWeight);
		sb.append(", wave=").append(wave);
		sb.append('}');
		return sb.toString();
	}

}
