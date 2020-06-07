package com.snailyy.numberapplication.utils;

import android.os.Process;
import android.util.Log;

/**
 * @author create by yongjie on 2018/6/29
 * Log工具类
 */
public class HiLogger {

	private static volatile boolean OUTPUT_LOG = true;

	public static void setLogEnable(boolean enable) {
		OUTPUT_LOG = enable;
	}

	public static void v(String tag, String message) {
		if (OUTPUT_LOG) {
			Log.v(tag, basicMessage(message));
		}
	}

	public static void v(String tag, String format, Object... obj) {
		if (OUTPUT_LOG) {
			String message = String.format(format, obj);
			Log.v(tag, basicMessage(message));
		}
	}


	public static void d(String tag, String message) {
		if (OUTPUT_LOG) {
			Log.d(tag, basicMessage(message));
		}
	}

	public static void d(String tag, String format, Object... obj) {
		if (OUTPUT_LOG) {
			Log.d(tag, basicMessage(format, obj));
		}
	}

	public static void i(String tag, String message) {
		if (OUTPUT_LOG) {
			Log.i(tag, basicMessage(message));
		}
	}

	public static void i(String tag, String format, Object... obj) {
		if (OUTPUT_LOG) {
			String message = String.format(format, obj);
			Log.i(tag, basicMessage(message));
		}
	}

	public static void w(String tag, String message) {
		if (OUTPUT_LOG) {
			Log.w(tag, basicMessage(message));
		}
	}

	public static void w(String tag, String format, Object... obj) {
		if (OUTPUT_LOG) {
			String message = String.format(format, obj);
			Log.w(tag, basicMessage(message));
		}
	}

	public static void e(String tag, String message) {
		if (OUTPUT_LOG) {
			Log.e(tag, basicMessage(message));
		}
	}

	public static void e(String tag, String message, Throwable throwable) {
		if (OUTPUT_LOG) {
			Log.e(tag, basicMessage(message), throwable);
		}
	}

	private static String basicMessage(String message, Object... objects) {
		String handleMessage = buildHandleMessage(message);
		String formatMessage = buildFormatMessage(handleMessage);
		Object[] finalParams = buildFormatObjects(handleMessage, objects);
		return String.format(formatMessage, finalParams);
	}

	private static String buildHandleMessage(String message) {
		return message.replaceAll("\\{\\}", "%s");
	}

	private static Object[] buildFormatObjects(String newMessage, Object[] objects) {
		int charNumber = findReplaceCharsNumber(newMessage);
		Object[] finalParams = new Object[charNumber + 1];
		finalParams[0] = basicHeadMessage();
		//存在%s这个特殊的占位符
		if (charNumber > 0 && objects != null) {
			if (objects.length - finalParams.length - 1 >= 0) {
				//参数比%s多的情况截取处理
				for (int i = 0; i < finalParams.length - 1; i++) {
					finalParams[i + 1] = objects[i];
				}
			} else {
				//参数比%s少的情况填充处理
				for (int i = 0; i < objects.length; i++) {
					finalParams[i + 1] = objects[i];
				}
			}
		}
		return finalParams;
	}

	private static String buildFormatMessage(String newMessage) {
		StringBuilder contentBuilder = new StringBuilder();
		contentBuilder.append("%s ")
				.append(newMessage);
		return contentBuilder.toString();
	}

	/**
	 * 查找字符串中%s的个数
	 */
	private static int findReplaceCharsNumber(String message) {
		int formatCount = 0;
		int flag = 0;
		char[] chars = message.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			if (chars[i] == '%') {
				flag = 1;
			} else if (chars[i] == 's') {
				if (flag == 1) {
					formatCount++;
				}
				flag = 0;
			} else {
				flag = 0;
			}
		}
		return formatCount;
	}

	private static String basicHeadMessage() {
		StringBuilder basicBuilder = new StringBuilder();
		basicBuilder.append("[")
//                .append(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"). 这一步时间转换特别耗时1ms以上
//                        format(new Date(System.currentTimeMillis())))
				.append(Process.myPid())
				.append("/").append(Thread.currentThread().getId())
				.append(" ").append(Thread.currentThread().getName())
				.append("]");
		return basicBuilder.toString();
	}


	/**
	 * 打印方法栈帧信息，写5的原因是根据栈的入栈规律，第5个元素就是我们需要的信息
	 * 暂时测试没有问题
	 */
	private static String getMethodMessage() {
		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		StringBuilder builder = new StringBuilder();
		String className = stackTrace[5].getClassName();
		String methodName = stackTrace[5].getMethodName();
		int lineName = stackTrace[5].getLineNumber();
		builder.append("[").append(className).append(", ").append(methodName).append("(), ").append(lineName).append("]");
		return builder.toString();
	}
}

