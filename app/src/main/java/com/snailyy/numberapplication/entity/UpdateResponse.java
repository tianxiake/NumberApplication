package com.snailyy.numberapplication.entity;

import java.io.Serializable;

public class UpdateResponse implements Serializable {
    public int status;
    public String desc;

    public UpdateResponse(int status, String desc) {
        this.status = status;
        this.desc = desc;
    }

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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UpdateResponse{");
        sb.append("status=").append(status);
        sb.append(", desc='").append(desc).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
