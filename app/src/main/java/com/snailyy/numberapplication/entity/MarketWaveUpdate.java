package com.snailyy.numberapplication.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MarketWaveUpdate implements Serializable {
	public static final String UPDATE_MARKET = "updateMarket";
	public static final String UPDATE_MSS = "updateMss";
	public static final String UPDATE_ALL = "updateAll";

	private String updateKey = UPDATE_ALL;
	private UpdateMarket updateMarket;
	private UpdateMss updateMss;

	public String getUpdateKey() {
		return updateKey;
	}

	public void setUpdateKey(String updateKey) {
		this.updateKey = updateKey;
	}

	public UpdateMarket getUpdateMarket() {
		return updateMarket;
	}

	public void setUpdateMarket(UpdateMarket updateMarket) {
		this.updateMarket = updateMarket;
	}

	public UpdateMss getUpdateMss() {
		return updateMss;
	}

	public void setUpdateMss(UpdateMss updateMss) {
		this.updateMss = updateMss;
	}

	public static class UpdateMarket {
		public static final String UPDATE_MARKET_LOOK_DOWN = "lookDown";
		public static final String UPDATE_MARKET_LOOK_UP = "lookup";
		public static final String UPDATE_LOOK_AWFUL_DOWN = "lookAwfulDown";
		public static final String UPDATE_ALL = "all";

		private String updateKey = UPDATE_ALL;
		private List<MarketExpect> marketExpects = new ArrayList<>();

		public String getUpdateKey() {
			return updateKey;
		}

		public void setUpdateKey(String updateKey) {
			this.updateKey = updateKey;
		}

		public List<MarketExpect> getMarketExpects() {
			return marketExpects;
		}

		public void setMarketExpects(List<MarketExpect> marketExpects) {
			this.marketExpects = marketExpects;
		}

		@Override
		public String toString() {
			final StringBuilder sb = new StringBuilder("UpdateMarket{");
			sb.append("updateKey='").append(updateKey).append('\'');
			sb.append(", marketExpects=").append(marketExpects);
			sb.append('}');
			return sb.toString();
		}
	}

	public static class UpdateMss {
		private List<MssWarn> mssWarns = new ArrayList<>();

		public List<MssWarn> getMssWarns() {
			return mssWarns;
		}

		public void setMssWarns(List<MssWarn> mssWarns) {
			this.mssWarns = mssWarns;
		}

		@Override
		public String toString() {
			final StringBuilder sb = new StringBuilder("UpdateMss{");
			sb.append("mssWarns=").append(mssWarns);
			sb.append('}');
			return sb.toString();
		}
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("MarketWaveUpdate{");
		sb.append("updateKey='").append(updateKey).append('\'');
		sb.append(", updateMarket=").append(updateMarket);
		sb.append(", updateMss=").append(updateMss);
		sb.append('}');
		return sb.toString();
	}
}


