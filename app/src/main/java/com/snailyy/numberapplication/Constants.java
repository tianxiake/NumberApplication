package com.snailyy.numberapplication;

/**
 * @author yongjie created on 2020/4/18.
 */
public class Constants {

	public static final String MARKET = "market";
	public static final String OPERATION = "operation";
	public static final String MY = "my";

	public static final int HTTP_STATUS_SUCCESS_CODE = 200;
	public static final int HTTP_STATUS_PARAMS_ERROR = 401;
	public static final int REQUEST_TOO_FREQUENTLY_ERROR = 402;
	public static final int REQUEST_SERVER_ERROR = 500;

	public static final String VERSION = "1.0.7";
	/**
	 * 启动服务
	 */
	public static final String COMMAND_START = "1";
	/**
	 * 停止服务
	 */
	public static final String COMMAND_STOP = "0";
	/**
	 * 自动模式根据预设的购买比例自动操作
	 */
	public static final String AUTO_MODE = "auto";
	/**
	 * 手动模式,由人工进行操作
	 */
	public static final String MANUAL_MODE = "manual";

	/**
	 * 预计后市会涨
	 */
	public static final String LOOK_UP = "lookup";
	/**
	 * 预计后市会跌
	 */
	public static final String LOOK_DOWN = "lookdown";

	/**
	 * 预计后市会极度下跌
	 */
	public static final String LOOK_AWFUL_DOWN = "lookawfuldown";

	/**
	 * 预计后市会震荡
	 */
	public static final String LOOK_WAVE = "lookwave";

	/**
	 * 启动命令不符合取值范围
	 */
	public static final int MESSAGE_CODE = 1111;

	/**
	 * 停止成功
	 */
	public static final int MESSAGE_STOP_SUCCESS = 2222;
	public static final int MESSAGE_STOP_FAILURE = 3333;

	public static final int MESSAGE_START_SUCCESS = 4444;


	public static final String ETH_SYMBOLS = "ethusdt";
	public static final String ETH_CURRENCY = "eth";
	public static final String USDT_CURRENCY = "usdt";


	/**
	 * usdtPrice
	 */
	public static final String PARAMS_USDT = "usdtPrice";
	/**
	 * 是否让系统处于等待状态
	 */
	public static final String PARAMS_WAIT = "isWait";

	/**
	 * 默认盈利比例
	 */
	public static final String PARAMS_DEFAULT_PROFIT_PERCENT = "defaultProfitPercent";
	/**
	 * 止损比例
	 */
	public static final String PARAMS_STOP_LOSS_PERCENT = "stopLossPercent";
	/**
	 * 市场预期
	 */
	public static final String PARAMS_CURRENT_MARKET_EXPECTS = "currentMarketExpects";
	/**
	 * 单日暴跌比例
	 */
	public static final String PARAMS_DAY_CRASH_PERCENT = "dayCrashPercent";
	/**
	 * 市场周期性暴跌比例
	 */
	public static final String PARAMS_CRASH_WAIT_PERCENT = "crashWaitPercent";
	/**
	 * 暴涨比例
	 */
	public static final String PARAMS_BOOM_WAIT_PERCENT = "boomWaitPercent";
	/**
	 * 运行模式
	 */
	public static final String PARAMS_RUN_MODE = "runMode";

	public static final String CURRENCY_ETH_KEY = "eth";
	public static final String CURRENCY_USDT_KEY = "usdt";
}
