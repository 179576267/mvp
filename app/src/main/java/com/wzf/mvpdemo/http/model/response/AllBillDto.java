package com.wzf.mvpdemo.http.model.response;

/**
 * 全部账单
 * Created by kjg.
 */

public class AllBillDto {
    private String type;
    private String time;
    private String money;
    private String balance;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
}
