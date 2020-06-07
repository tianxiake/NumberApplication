package com.snailyy.numberapplication.entity;

/**
 * @author yongjie created on 2020/4/27.
 */
public class HealthEntity {

	private String title;
	private String time;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("HealthEntity{");
		sb.append("title='").append(title).append('\'');
		sb.append(", time='").append(time).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
