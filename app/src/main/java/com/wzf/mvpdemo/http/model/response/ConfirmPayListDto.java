package com.wzf.mvpdemo.http.model.response;

/**
 * Created by kjg.
 */

public class ConfirmPayListDto {


    /**
     * orderId : 37
     * shopId : 1
     * shopOrderNO : 20170321192625000
     * shopTableNo : 1
     * price : 229.0
     * confirmPrice : 0.0
     * confirmOrderTime : 1490164622000
     */

    private String orderId;
    private String shopId;
    private String shopOrderNO;
    private String shopTableNo;
    private String price;
    private String confirmPrice;
    private String confirmOrderTime;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getShopOrderNO() {
        return shopOrderNO;
    }

    public void setShopOrderNO(String shopOrderNO) {
        this.shopOrderNO = shopOrderNO;
    }

    public String getShopTableNo() {
        return shopTableNo;
    }

    public void setShopTableNo(String shopTableNo) {
        this.shopTableNo = shopTableNo;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getConfirmPrice() {
        return confirmPrice;
    }

    public void setConfirmPrice(String confirmPrice) {
        this.confirmPrice = confirmPrice;
    }

    public String getConfirmOrderTime() {
        return confirmOrderTime;
    }

    public void setConfirmOrderTime(String confirmOrderTime) {
        this.confirmOrderTime = confirmOrderTime;
    }

    @Override
    public String toString() {
        return "ConfirmPayListDto{" +
                "orderId='" + orderId + '\'' +
                ", shopId='" + shopId + '\'' +
                ", shopOrderNO='" + shopOrderNO + '\'' +
                ", shopTableNo='" + shopTableNo + '\'' +
                ", price='" + price + '\'' +
                ", confirmPrice='" + confirmPrice + '\'' +
                ", confirmOrderTime='" + confirmOrderTime + '\'' +
                '}';
    }
}
