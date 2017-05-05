package com.wzf.mvpdemo.http.model.request;

/**
 * Created by kjg.
 */
public class CancelOrderParam extends PostParam {
    private String orderId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
