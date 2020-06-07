package com.snailyy.numberapplication.entity;

import com.snailyy.numberapplication.entity.ETHConfig;
import com.snailyy.numberapplication.entity.VdsConfig;

import java.io.Serializable;

public class SwitchRequest implements Serializable {
	public static final String TAG = "SwitchRequest";

	/**
	 * usdt的价格
	 */
	private float usdtPrice;
	/**
	 * 运行模式 手动还是自动模式
	 */
	private String runMode;
	/**
	 * 启动命令
	 */
	private String command;

	private ETHConfig ethConfig;

	/**
	 * Vds相关监控
	 */
	private VdsConfig vdsConfig;

	public float getUsdtPrice() {
		return usdtPrice;
	}

	public void setUsdtPrice(float usdtPrice) {
		this.usdtPrice = usdtPrice;
	}

	public String getRunMode() {
		return runMode;
	}

	public void setRunMode(String runMode) {
		this.runMode = runMode;
	}

	public ETHConfig getEthConfig() {
		return ethConfig;
	}

	public void setEthConfig(ETHConfig ethConfig) {
		this.ethConfig = ethConfig;
	}

	public VdsConfig getVdsConfig() {
		return vdsConfig;
	}

	public void setVdsConfig(VdsConfig vdsConfig) {
		this.vdsConfig = vdsConfig;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("SwitchRequest{");
		sb.append("usdtPrice=").append(usdtPrice);
		sb.append(", runMode='").append(runMode).append('\'');
		sb.append(", command='").append(command).append('\'');
		sb.append(", ethConfig=").append(ethConfig);
		sb.append(", vdsConfig=").append(vdsConfig);
		sb.append('}');
		return sb.toString();
	}
}



