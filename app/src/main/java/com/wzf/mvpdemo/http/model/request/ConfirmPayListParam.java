package com.wzf.mvpdemo.http.model.request;

/**
 * Created by kjg.
 */
public class ConfirmPayListParam extends PostParam {
    private String type;
    private String confirmOrderTime;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getConfirmOrderTime() {
        return confirmOrderTime;
    }

    public void setConfirmOrderTime(String confirmOrderTime) {
        this.confirmOrderTime = confirmOrderTime;
    }
}
