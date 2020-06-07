package com.snailyy.numberapplication;

/**
 * @author yongjie created on 2020/4/22.
 */
public class TitleEntity implements EntityInterface{
	public String title;
	public String message;

	public TitleEntity(String title, String message) {
		this.title = title;
		this.message = message;
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
		final StringBuilder sb = new StringBuilder("TitleEntity{");
		sb.append("title='").append(title).append('\'');
		sb.append(", message='").append(message).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
