package com.wzf.mvpdemo.http.model.response;

/**
 * @Description:
 * @author: wangzhenfei
 * @date: 2017-03-21 16:17
 */

public class BossPageResponseDto {
    private int totalMoney;
    private int extractingMoney;

    public int getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(int totalMoney) {
        this.totalMoney = totalMoney;
    }

    public int getExtractingMoney() {
        return extractingMoney;
    }

    public void setExtractingMoney(int extractingMoney) {
        this.extractingMoney = extractingMoney;
    }

    @Override
    public String toString() {
        return "BossPageResponseDao{" +
                "tatalMoney=" + totalMoney +
                ", extractingMoney=" + extractingMoney +
                '}';
    }
}
