package com.snailyy.numberapplication.entity;
import com.snailyy.numberapplication.entity.AccountCurrencyEntity;
import com.snailyy.numberapplication.entity.DayData;
import com.snailyy.numberapplication.entity.HealthTimeEntity;
import com.snailyy.numberapplication.entity.MarketExpect;
import com.snailyy.numberapplication.entity.MssWarn;
import com.snailyy.numberapplication.entity.SurvivalEntity;
import com.snailyy.numberapplication.entity.TradeEntity;
import com.snailyy.numberapplication.entity.VdsConfig;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class QueryResponseEntity implements Serializable {

	private int status;

	private String desc;

	private String runMode;
	private String wait;

	private String command;

	private float ethNewPrice;
	private float ethOpenPrice;

	/**
	 * 市场深度卖单的最低价
	 */
	private float askDepthLowPrice = -1;

	/**
	 * 市场深度买单的最高价
	 */
	private float bidDepthHighPrice = -1;

	/**
	 * 止损比例 [-1,0] 0.35 表示下跌超过35%就全部抛出
	 */
	private float stopLossPercent = -1;
	/**
	 * 止盈比例 [0-1] 0.35 表示下跌超过35%就全部抛出
	 */
	private float stopProfitPercent = -1;

	/**
	 * 默认的盈利比例 [0-1] 0.035 这个是比如买了5个币 如果这5个币到达了 3.5% 盈利比例就卖出
	 */
	private float defaultProfitPercent = -1;

	/**
	 * 当前市场期望走向
	 */
	private String currentMarketExpects;

	/**
	 * 市场预期的集合
	 */
	private List<MarketExpect> marketExpects;

	/**
	 * 单日市场大跌的比例  [-1,0] 单日如果大跌过大 后几日下跌的可能性非常大
	 */
	private float dayCrashPercent = -1;

	/**
	 * 市场大跌的比例 [-1,0] 周期性的比例 比如三天 5天等
	 */
	private float crashWaitPercent = -1;

	/**
	 * 市场暴涨比例 [0-1] 周期性的比例 比如三天 5天等
	 */
	private float boomWaitPercent;

	/**
	 * 当前所有的币的信息
	 */
	private List<AccountCurrencyEntity> currency;


	/**
	 * 卖出失败的数据集合
	 */
	private List<TradeEntity> sellFailList;

	/**
	 * 一周的波动信息
	 */
	private List<WeekWave> weekWaves;

	/**
	 * 一周的开盘价格
	 */
	private List<DayData> weekOpenDayData;

	/**
	 * 短信报警提示信息
	 */
	private List<MssWarn> mssWarns;

	/**
	 * 已经购买的币的集合
	 */
	private List<TradeEntity> buyList;

	/**
	 * 存活时间单位是毫秒
	 */
	private long healthTime;
	private boolean orderServiceIsSurvivel;
	private boolean priceServiceIsSurvivel;
	private boolean accountServiceIsSurvivel;
	private boolean marketDepthServiceIsSurvivel;
	private boolean tradeServiceIsSurvivel;
	private List<SurvivalEntity> survivleEntities = new ArrayList<>();

	private VdsConfig vdsConfig;
	private boolean vdsServiceIsLive;
	private int vdsServiceReconnectTimes;
	private List<HealthTimeEntity> vdsServicesHealthTimes;
	private float vdsNewPrice;
	private float vdsOpenPrice;
	private String vdsUrl;
	private float ethBuyCount;
	private float ethBuyAverage;

	/**
	 * 短时间内交易异常的报警数量
	 */
	private int tradeBoomCount;
	private int tradeDayMssCount;
	private int tradeTimeCount;

	public boolean isOrderServiceIsSurvivel() {
		return orderServiceIsSurvivel;
	}

	public boolean isTradeServiceIsSurvivel() {
		return tradeServiceIsSurvivel;
	}

	public int getTradeBoomCount() {
		return tradeBoomCount;
	}

	public void setTradeBoomCount(int tradeBoomCount) {
		this.tradeBoomCount = tradeBoomCount;
	}

	public int getTradeDayMssCount() {
		return tradeDayMssCount;
	}

	public void setTradeDayMssCount(int tradeDayMssCount) {
		this.tradeDayMssCount = tradeDayMssCount;
	}

	public void setTradeServiceIsSurvivel(boolean tradeServiceIsSurvivel) {
		this.tradeServiceIsSurvivel = tradeServiceIsSurvivel;
	}

	public void setOrderServiceIsSurvivel(boolean orderServiceIsSurvivel) {
		this.orderServiceIsSurvivel = orderServiceIsSurvivel;
	}

	public List<TradeEntity> getBuyList() {
		return buyList;
	}

	public void setBuyList(List<TradeEntity> buyList) {
		this.buyList = buyList;
	}

	public boolean isPriceServiceIsSurvivel() {
		return priceServiceIsSurvivel;
	}

	public void setPriceServiceIsSurvivel(boolean priceServiceIsSurvivel) {
		this.priceServiceIsSurvivel = priceServiceIsSurvivel;
	}

	public boolean isAccountServiceIsSurvivel() {
		return accountServiceIsSurvivel;
	}

	public void setAccountServiceIsSurvivel(boolean accountServiceIsSurvivel) {
		this.accountServiceIsSurvivel = accountServiceIsSurvivel;
	}

	public boolean isMarketDepthServiceIsSurvivel() {
		return marketDepthServiceIsSurvivel;
	}

	public void setMarketDepthServiceIsSurvivel(boolean marketDepthServiceIsSurvivel) {
		this.marketDepthServiceIsSurvivel = marketDepthServiceIsSurvivel;
	}

	public List<SurvivalEntity> getSurvivleEntities() {
		return survivleEntities;
	}

	public int getTradeTimeCount() {
		return tradeTimeCount;
	}

	public void setTradeTimeCount(int tradeTimeCount) {
		this.tradeTimeCount = tradeTimeCount;
	}

	public void setSurvivleEntities(List<SurvivalEntity> survivleEntities) {
		this.survivleEntities = survivleEntities;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getRunMode() {
		return runMode;
	}

	public void setRunMode(String runMode) {
		this.runMode = runMode;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
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

	public String getCurrentMarketExpects() {
		return currentMarketExpects;
	}

	public void setCurrentMarketExpects(String currentMarketExpects) {
		this.currentMarketExpects = currentMarketExpects;
	}

	public List<MarketExpect> getMarketExpects() {
		return marketExpects;
	}

	public void setMarketExpects(List<MarketExpect> marketExpects) {
		this.marketExpects = marketExpects;
	}

	public float getDayCrashPercent() {
		return dayCrashPercent;
	}

	public void setDayCrashPercent(float dayCrashPercent) {
		this.dayCrashPercent = dayCrashPercent;
	}

	public float getCrashWaitPercent() {
		return crashWaitPercent;
	}

	public void setCrashWaitPercent(float crashWaitPercent) {
		this.crashWaitPercent = crashWaitPercent;
	}

	public float getBoomWaitPercent() {
		return boomWaitPercent;
	}

	public void setBoomWaitPercent(float boomWaitPercent) {
		this.boomWaitPercent = boomWaitPercent;
	}

	public List<AccountCurrencyEntity> getCurrency() {
		return currency;
	}

	public void setCurrency(List<AccountCurrencyEntity> currency) {
		this.currency = currency;
	}


	public List<TradeEntity> getSellFailList() {
		return sellFailList;
	}

	public void setSellFailList(List<TradeEntity> sellFailList) {
		this.sellFailList = sellFailList;
	}

	public List<WeekWave> getWeekWaves() {
		return weekWaves;
	}

	public void setWeekWaves(List<WeekWave> weekWaves) {
		this.weekWaves = weekWaves;
	}


	public long getHealthTime() {
		return healthTime;
	}

	public void setHealthTime(long healthTime) {
		this.healthTime = healthTime;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public List<MssWarn> getMssWarns() {
		return mssWarns;
	}

	public void setMssWarns(List<MssWarn> mssWarns) {
		this.mssWarns = mssWarns;
	}

	public float getEthNewPrice() {
		return ethNewPrice;
	}

	public void setEthNewPrice(float ethNewPrice) {
		this.ethNewPrice = ethNewPrice;
	}


	public float getAskDepthLowPrice() {
		return askDepthLowPrice;
	}

	public void setAskDepthLowPrice(float askDepthLowPrice) {
		this.askDepthLowPrice = askDepthLowPrice;
	}

	public float getBidDepthHighPrice() {
		return bidDepthHighPrice;
	}

	public void setBidDepthHighPrice(float bidDepthHighPrice) {
		this.bidDepthHighPrice = bidDepthHighPrice;
	}

	public String getWait() {
		return wait;
	}

	public void setWait(String wait) {
		this.wait = wait;
	}

	public VdsConfig getVdsConfig() {
		return vdsConfig;
	}

	public void setVdsConfig(VdsConfig vdsConfig) {
		this.vdsConfig = vdsConfig;
	}

	public boolean isVdsServiceIsLive() {
		return vdsServiceIsLive;
	}

	public void setVdsServiceIsLive(boolean vdsServiceIsLive) {
		this.vdsServiceIsLive = vdsServiceIsLive;
	}

	public float getVdsNewPrice() {
		return vdsNewPrice;
	}

	public void setVdsNewPrice(float vdsNewPrice) {
		this.vdsNewPrice = vdsNewPrice;
	}

	public float getEthOpenPrice() {
		return ethOpenPrice;
	}

	public void setEthOpenPrice(float ethOpenPrice) {
		this.ethOpenPrice = ethOpenPrice;
	}

	public float getVdsOpenPrice() {
		return vdsOpenPrice;
	}

	public void setVdsOpenPrice(float vdsOpenPrice) {
		this.vdsOpenPrice = vdsOpenPrice;
	}

	public int getVdsServiceReconnectTimes() {
		return vdsServiceReconnectTimes;
	}

	public void setVdsServiceReconnectTimes(int vdsServiceReconnectTimes) {
		this.vdsServiceReconnectTimes = vdsServiceReconnectTimes;
	}

	public List<HealthTimeEntity> getVdsServicesHealthTimes() {
		return vdsServicesHealthTimes;
	}

	public void setVdsServicesHealthTimes(List<HealthTimeEntity> vdsServicesHealthTimes) {
		this.vdsServicesHealthTimes = vdsServicesHealthTimes;
	}

	public String getVdsUrl() {
		return vdsUrl;
	}

	public void setVdsUrl(String vdsUrl) {
		this.vdsUrl = vdsUrl;
	}

	public List<DayData> getWeekOpenDayData() {
		return weekOpenDayData;
	}

	public void setWeekOpenDayData(List<DayData> weekOpenDayData) {
		this.weekOpenDayData = weekOpenDayData;
	}

	public float getEthBuyCount() {
		return ethBuyCount;
	}

	public void setEthBuyCount(float ethBuyCount) {
		this.ethBuyCount = ethBuyCount;
	}

	public float getEthBuyAverage() {
		return ethBuyAverage;
	}

	public void setEthBuyAverage(float ethBuyAverage) {
		this.ethBuyAverage = ethBuyAverage;
	}

	public static class WeekWave {
		public String key;
		public float wavePercent;

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public float getWavePercent() {
			return wavePercent;
		}

		public void setWavePercent(float wavePercent) {
			this.wavePercent = wavePercent;
		}

		public WeekWave() {
		}

		public WeekWave(String key, float wavePercent) {
			this.key = key;
			this.wavePercent = wavePercent;
		}

		@Override
		public String toString() {
			final StringBuilder sb = new StringBuilder("WeekWave{");
			sb.append("key='").append(key).append('\'');
			sb.append(", wavePercent='").append(wavePercent).append('\'');
			sb.append('}');
			return sb.toString();
		}
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("QueryResponseEntity{");
		sb.append("status=").append(status);
		sb.append(", desc='").append(desc).append('\'');
		sb.append(", runMode='").append(runMode).append('\'');
		sb.append(", wait='").append(wait).append('\'');
		sb.append(", command='").append(command).append('\'');
		sb.append(", ethNewPrice=").append(ethNewPrice);
		sb.append(", ethOpenPrice=").append(ethOpenPrice);
		sb.append(", askDepthLowPrice=").append(askDepthLowPrice);
		sb.append(", bidDepthHighPrice=").append(bidDepthHighPrice);
		sb.append(", stopLossPercent=").append(stopLossPercent);
		sb.append(", stopProfitPercent=").append(stopProfitPercent);
		sb.append(", defaultProfitPercent=").append(defaultProfitPercent);
		sb.append(", currentMarketExpects='").append(currentMarketExpects).append('\'');
		sb.append(", marketExpects=").append(marketExpects);
		sb.append(", dayCrashPercent=").append(dayCrashPercent);
		sb.append(", crashWaitPercent=").append(crashWaitPercent);
		sb.append(", boomWaitPercent=").append(boomWaitPercent);
		sb.append(", currency=").append(currency);
		sb.append(", sellFailList=").append(sellFailList);
		sb.append(", weekWaves=").append(weekWaves);
		sb.append(", weekOpenDayData=").append(weekOpenDayData);
		sb.append(", mssWarns=").append(mssWarns);
		sb.append(", buyList=").append(buyList);
		sb.append(", healthTime=").append(healthTime);
		sb.append(", orderServiceIsSurvivel=").append(orderServiceIsSurvivel);
		sb.append(", priceServiceIsSurvivel=").append(priceServiceIsSurvivel);
		sb.append(", accountServiceIsSurvivel=").append(accountServiceIsSurvivel);
		sb.append(", marketDepthServiceIsSurvivel=").append(marketDepthServiceIsSurvivel);
		sb.append(", tradeServiceIsSurvivel=").append(tradeServiceIsSurvivel);
		sb.append(", survivleEntities=").append(survivleEntities);
		sb.append(", vdsConfig=").append(vdsConfig);
		sb.append(", vdsServiceIsLive=").append(vdsServiceIsLive);
		sb.append(", vdsServiceReconnectTimes=").append(vdsServiceReconnectTimes);
		sb.append(", vdsServicesHealthTimes=").append(vdsServicesHealthTimes);
		sb.append(", vdsNewPrice=").append(vdsNewPrice);
		sb.append(", vdsOpenPrice=").append(vdsOpenPrice);
		sb.append(", vdsUrl='").append(vdsUrl).append('\'');
		sb.append(", ethBuyCount=").append(ethBuyCount);
		sb.append(", ethBuyAverage=").append(ethBuyAverage);
		sb.append(", tradeBoomCount=").append(tradeBoomCount);
		sb.append(", tradeDayMssCount=").append(tradeDayMssCount);
		sb.append(", tradeTimeCount=").append(tradeTimeCount);
		sb.append('}');
		return sb.toString();
	}
}
