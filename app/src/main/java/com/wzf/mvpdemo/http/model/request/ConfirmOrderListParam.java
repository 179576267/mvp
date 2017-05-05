package com.wzf.mvpdemo.http.model.request;

/**
 * Created by kjg.
 */

public class ConfirmOrderListParam extends PostParam {

    private String lastOrderId;

    public String getLastOrderId() {
        return lastOrderId;
    }

    public void setLastOrderId(String lastOrderId) {
        this.lastOrderId = lastOrderId;
    }

}
