package com.snailyy.numberapplication;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

/**
 * @author yongjie created on 2020/4/18.
 */
public class NumberUtils {

	public static String getScaleStr(double value) {
		return getScaleStr(value, 4);
	}

	public static String getScaleStr(double value, int newScale) {
		try {
			BigDecimal bigDecimal = new BigDecimal(value);
			bigDecimal = bigDecimal.setScale(newScale, BigDecimal.ROUND_DOWN);
			float v = bigDecimal.floatValue();
			return v + "";
		} catch (Exception e) {
		}
		return "";
	}

	public static float getScaleFloat(double value, int newScale) {
		try {
			BigDecimal bigDecimal = new BigDecimal(value);
			bigDecimal = bigDecimal.setScale(newScale, BigDecimal.ROUND_DOWN);
			float v = bigDecimal.floatValue();
			return v;
		} catch (Exception e) {
		}
		return -1;
	}

	public static String getPercentStr(double value) {
		try {
			DecimalFormat df = new DecimalFormat("0.00%");
			return df.format(value);
		} catch (Exception e) {
		}
		return "";
	}

	public static String formatTime(long time) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		return simpleDateFormat.format(time);
	}

}
