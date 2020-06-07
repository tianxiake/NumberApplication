package com.snailyy.numberapplication.entity;

import com.snailyy.numberapplication.entity.MssWarn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class VdsConfig {
    private List<MssWarn> mssWarns;

    public List<MssWarn> getMssWarns() {
        return mssWarns;
    }

    public void setMssWarns(List<MssWarn> mssWarns) {
        List<MssWarn> tempMssWarns = new ArrayList<>();
        //mssWarns按照从小到大进行排序 比如 -5 -4 -3 0 1
        Collections.sort(mssWarns, new Comparator<MssWarn>() {
            @Override
            public int compare(MssWarn o1, MssWarn o2) {
                if (o1.getWarnPercent() > o2.getWarnPercent()) {
                    return 1;
                } else if (o1.getWarnPercent() == o2.getWarnPercent()) {
                    tempMssWarns.add(o1);
                    return 1;
                }
                return -1;
            }
        });
        for (int i = 0; i < tempMssWarns.size(); i++) {
            mssWarns.remove(tempMssWarns.get(i));
        }
        this.mssWarns = mssWarns;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("VdsConfig{");
        sb.append("mssWarns=").append(mssWarns);
        sb.append('}');
        return sb.toString();
    }
}
