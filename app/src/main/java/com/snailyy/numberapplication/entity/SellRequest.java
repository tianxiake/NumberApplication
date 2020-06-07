package com.snailyy.numberapplication.entity;

import java.io.Serializable;
import java.util.Objects;

public class SellRequest implements Serializable {
    /**
     * sell订单号
     */
    private long tradeId;

    /**
     * 卖出的数量
     */
    private float sellVolume;

    /**
     * 交易时间
     */
    private long sellTime;

    public long getTradeId() {
        return tradeId;
    }

    public void setTradeId(long tradeId) {
        this.tradeId = tradeId;
    }


    public float getSellVolume() {
        return sellVolume;
    }

    public void setSellVolume(float sellVolume) {
        this.sellVolume = sellVolume;
    }

    public long getSellTime() {
        return sellTime;
    }

    public void setSellTime(long sellTime) {
        this.sellTime = sellTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SellRequest that = (SellRequest) o;
        return tradeId == that.tradeId &&
                Float.compare(that.sellVolume, sellVolume) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(tradeId, sellVolume);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SellRequest{");
        sb.append("tradeId=").append(tradeId);
        sb.append(", tradeVolume=").append(sellVolume);
        sb.append(", tradeTime=").append(sellTime);
        sb.append('}');
        return sb.toString();
    }
}
