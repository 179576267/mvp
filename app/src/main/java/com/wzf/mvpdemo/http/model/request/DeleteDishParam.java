package com.wzf.mvpdemo.http.model.request;

/**
 * Created by kjg
 */
public class DeleteDishParam extends PostParam {


    /**
     * orderId : 1
     * dishId : 1
     * type :
     */

    private String orderId;
    private String dishId;
    private String type;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getDishId() {
        return dishId;
    }

    public void setDishId(String dishId) {
        this.dishId = dishId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
