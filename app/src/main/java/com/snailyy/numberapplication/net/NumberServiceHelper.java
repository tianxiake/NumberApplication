package com.snailyy.numberapplication.net;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author yongjie created on 2020/4/18.
 */
public class NumberServiceHelper {
	private static volatile NumberServiceHelper numberServiceHelper;
	private NumberService numberService;


	private NumberServiceHelper() {
		Retrofit build = new Retrofit.Builder()
				.baseUrl("http://47.252.6.252:8080/")
				.addConverterFactory(GsonConverterFactory.create())
				.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
				.build();
		numberService = build.create(NumberService.class);
	}

	public static NumberServiceHelper getInstance() {
		if (numberServiceHelper == null) {
			synchronized (NumberServiceHelper.class) {
				if (numberServiceHelper == null) {
					numberServiceHelper = new NumberServiceHelper();
				}
			}
		}
		return numberServiceHelper;
	}

	public NumberService getNumberService() {
		return numberService;
	}
}
