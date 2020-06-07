package com.snailyy.numberapplication.entity;

import java.io.Serializable;

public class PriceResponse implements Serializable {
    private int status;
    private String desc;
    private float currentPrice;
    private float openPrice;
    private float askPrice;
    private float bidPrice;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public float getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(float currentPrice) {
        this.currentPrice = currentPrice;
    }

    public float getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(float openPrice) {
        this.openPrice = openPrice;
    }

    public float getAskPrice() {
        return askPrice;
    }

    public void setAskPrice(float askPrice) {
        this.askPrice = askPrice;
    }

    public float getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(float bidPrice) {
        this.bidPrice = bidPrice;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PriceResponse{");
        sb.append("status=").append(status);
        sb.append(", desc='").append(desc).append('\'');
        sb.append(", currentPrice=").append(currentPrice);
        sb.append(", openPrice=").append(openPrice);
        sb.append(", askPrice=").append(askPrice);
        sb.append(", bidPrice=").append(bidPrice);
        sb.append('}');
        return sb.toString();
    }
}
