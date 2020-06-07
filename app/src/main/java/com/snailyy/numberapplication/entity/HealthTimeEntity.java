package com.snailyy.numberapplication.entity;

/**
 * @author yongjie created on 2020/5/5.
 */
public class HealthTimeEntity {
	private boolean health;

	private long time;

	public HealthTimeEntity() {
	}

	public HealthTimeEntity(boolean health, long time) {
		this.health = health;
		this.time = time;
	}

	public boolean isHealth() {
		return health;
	}

	public void setHealth(boolean health) {
		this.health = health;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("HealthTimeEntity{");
		sb.append("isHealth='").append(health).append('\'');
		sb.append(", time=").append(time);
		sb.append('}');
		return sb.toString();
	}
}
