package com.snailyy.numberapplication;

import com.snailyy.numberapplication.entity.QueryResponseEntity;

/**
 * @author yongjie created on 2020/4/18.
 */
public class MessageCenter {
	private static volatile MessageCenter messageCenter;

	private QueryResponseEntity queryResponseEntity;

	private MessageCenter() {

	}

	public static MessageCenter getInstance() {
		if (messageCenter == null) {
			synchronized (MessageCenter.class) {
				if (messageCenter == null) {
					messageCenter = new MessageCenter();
				}
			}
		}
		return messageCenter;
	}


	public QueryResponseEntity getQueryResponseEntity() {
		return queryResponseEntity;
	}

	public void setQueryResponseEntity(QueryResponseEntity queryResponseEntity) {
		this.queryResponseEntity = queryResponseEntity;
	}
}
