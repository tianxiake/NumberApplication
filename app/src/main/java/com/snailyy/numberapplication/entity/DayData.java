package com.snailyy.numberapplication.entity;

public class DayData {
    private String key;
    private float openPrice;

    public DayData(String key, float openPrice) {
        this.key = key;
        this.openPrice = openPrice;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public float getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(float openPrice) {
        this.openPrice = openPrice;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DayData{");
        sb.append("key='").append(key).append('\'');
        sb.append(", openPrice=").append(openPrice);
        sb.append('}');
        return sb.toString();
    }
}
