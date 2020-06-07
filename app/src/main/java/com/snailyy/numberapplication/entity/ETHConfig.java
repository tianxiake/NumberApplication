package com.snailyy.numberapplication.entity;
import com.snailyy.numberapplication.utils.HiLogger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ETHConfig {

	public static final String TAG = "ETHConfig";
	private String apiKey;

	private String secreteKey;
	/**
	 * 止损比例 负值[-1,0] -0.35 表示下跌超过35%就全部抛出
	 */
	private float stopLossPercent;
	/**
	 * 止盈比例 正值 [0-1] 0.35 表示下跌超过35%就全部抛出
	 */
	private float stopProfitPercent;

	/**
	 * 默认的盈利比例 [0-1] 0.035 这个是比如买了5个币 如果这5个币到达了 3.5% 盈利比例就卖出
	 */
	private float defaultProfitPercent = 0.1f;

	/**
	 * 短信报警比例
	 */
	private List<MssWarn> mssWarns = new ArrayList<>();

	public synchronized void setMssWarns(List<MssWarn> mssWarns) {
		List<MssWarn> tempMssWarns = new ArrayList<>();
		//mssWarns按照从小到大进行排序 比如 -5 -4 -3 0 1
		Collections.sort(mssWarns, new Comparator<MssWarn>() {
			@Override
			public int compare(MssWarn o1, MssWarn o2) {
				if (o1.getWarnPercent() > o2.getWarnPercent()) {
					return 1;
				} else if (o1.getWarnPercent() == o2.getWarnPercent()) {
					tempMssWarns.add(o1);
					return 1;
				}
				return -1;
			}
		});
		for (int i = 0; i < tempMssWarns.size(); i++) {
			mssWarns.remove(tempMssWarns.get(i));
		}
		HiLogger.d(TAG, "setMssWarns:{}", mssWarns);
		this.mssWarns = mssWarns;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getSecreteKey() {
		return secreteKey;
	}

	public void setSecreteKey(String secreteKey) {
		this.secreteKey = secreteKey;
	}

	public float getStopLossPercent() {
		return stopLossPercent;
	}

	public void setStopLossPercent(float stopLossPercent) {
		this.stopLossPercent = stopLossPercent;
	}

	public float getStopProfitPercent() {
		return stopProfitPercent;
	}

	public void setStopProfitPercent(float stopProfitPercent) {
		this.stopProfitPercent = stopProfitPercent;
	}

	public float getDefaultProfitPercent() {
		return defaultProfitPercent;
	}

	public void setDefaultProfitPercent(float defaultProfitPercent) {
		this.defaultProfitPercent = defaultProfitPercent;
	}

	public List<MssWarn> getMssWarns() {
		return mssWarns;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("ETHConfig{");
		sb.append("apiKey='").append(apiKey).append('\'');
		sb.append(", secreteKey='").append(secreteKey).append('\'');
		sb.append(", stopLossPercent=").append(stopLossPercent);
		sb.append(", stopProfitPercent=").append(stopProfitPercent);
		sb.append(", defaultProfitPercent=").append(defaultProfitPercent);
		sb.append(", mssWarns=").append(mssWarns);
		sb.append('}');
		return sb.toString();
	}
}
