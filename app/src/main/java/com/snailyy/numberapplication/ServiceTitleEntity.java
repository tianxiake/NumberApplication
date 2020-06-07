package com.snailyy.numberapplication;

/**
 * @author yongjie created on 2020/4/22.
 */
public class ServiceTitleEntity implements EntityInterface {
	public static final String TYPE_NORMAL = "normal";
	public static final String TYPE_WARN = "warn";
	public String title;
	public String message;
	public String type;
	public boolean isWarn = false;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isWarn() {
		return isWarn;
	}

	public void setWarn(boolean warn) {
		isWarn = warn;
	}

	public ServiceTitleEntity(String title, String message, String type) {
		this.title = title;
		this.message = message;
		this.type = type;
	}

	public ServiceTitleEntity(String title, String message, String type, boolean isWarn) {
		this.title = title;
		this.message = message;
		this.type = type;
		this.isWarn = isWarn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("ServiceTitleEntity{");
		sb.append("title='").append(title).append('\'');
		sb.append(", message='").append(message).append('\'');
		sb.append(", type='").append(type).append('\'');
		sb.append(", isLive=").append(isWarn);
		sb.append('}');
		return sb.toString();
	}
}
