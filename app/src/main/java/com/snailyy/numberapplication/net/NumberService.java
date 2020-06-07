package com.snailyy.numberapplication.net;

import com.snailyy.numberapplication.QueryResponseEntity;
import com.snailyy.numberapplication.entity.BuyRequest;
import com.snailyy.numberapplication.entity.BuyResponse;
import com.snailyy.numberapplication.entity.CommonResponse;
import com.snailyy.numberapplication.entity.MarketWaveUpdate;
import com.snailyy.numberapplication.entity.PriceResponse;
import com.snailyy.numberapplication.entity.SellRequest;
import com.snailyy.numberapplication.entity.SellResponse;
import com.snailyy.numberapplication.entity.SwitchRequest;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * @author yongjie created on 2020/4/18.
 */
public interface NumberService {

	/**
	 * 查看任务运行的信息
	 */
	@GET("query")
	Observable<QueryResponseEntity> getNumberRunMessage();

	/**
	 * 启动或者停止任务
	 */
	@POST("switch")
	Observable<CommonResponse> switchTask(@Body SwitchRequest switchRequest);

	/**
	 * 更新参数 以Post的方式
	 */
	@POST("update")
	Observable<CommonResponse> updateParams(@Body MarketWaveUpdate marketWaveUpdate);

	/**
	 * 更新参数 以GET请求参数的形式
	 */
	@GET("update")
	Observable<CommonResponse> updateSimpleParams(@QueryMap Map<String, String> queryMap);

	/**
	 * 购买操作
	 */
	@POST("buy")
	Observable<BuyResponse> buy(@Body BuyRequest buyRequest);

	/**
	 * 卖出操作
	 */
	@POST("sell")
	Observable<SellResponse> sell(@Body SellRequest buyRequest);

	/**
	 * 价格数据
	 */
	@GET("price")
	Observable<PriceResponse> getNewPrice();
}
