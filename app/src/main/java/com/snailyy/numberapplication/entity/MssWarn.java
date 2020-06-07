package com.snailyy.numberapplication.entity;

import java.io.Serializable;

/**
 * 短信报警的比例
 */
public class MssWarn implements Serializable {
    /**
     * 取值[-1,1]
     */
    private float warnPercent;

    public float getWarnPercent() {
        return warnPercent;
    }


    public void setWarnPercent(float warnPercent) {
        this.warnPercent = warnPercent;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MssWarn{");
        sb.append("warnPercent=").append(warnPercent);
        sb.append('}');
        return sb.toString();
    }
}
