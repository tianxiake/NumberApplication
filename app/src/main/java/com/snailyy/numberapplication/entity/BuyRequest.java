package com.snailyy.numberapplication.entity;

import java.io.Serializable;
import java.util.Objects;

public class BuyRequest implements Serializable {

    /**
     * 购买的比例 指定是当前可用币总额
     */
    private float buyWeight;

    /**
     * 购买期望的盈利比例 -1表示使用全局的收益比例
     */
    private float profitPercent = -1f;

    /**
     * 购买时间
     */
    private long buyTime;



    public float getBuyWeight() {
        return buyWeight;
    }

    public void setBuyWeight(float buyWeight) {
        this.buyWeight = buyWeight;
    }

    public float getProfitPercent() {
        return profitPercent;
    }

    public void setProfitPercent(float profitPercent) {
        this.profitPercent = profitPercent;
    }

    public long getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(long buyTime) {
        this.buyTime = buyTime;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BuyRequest that = (BuyRequest) o;
        return Float.compare(that.buyWeight, buyWeight) == 0 &&
                Float.compare(that.profitPercent, profitPercent) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(buyWeight, profitPercent);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BuyRequest{");
        sb.append("buyWeight=").append(buyWeight);
        sb.append(", profitPercent=").append(profitPercent);
        sb.append(", buyTime=").append(buyTime);
        sb.append('}');
        return sb.toString();
    }
}
