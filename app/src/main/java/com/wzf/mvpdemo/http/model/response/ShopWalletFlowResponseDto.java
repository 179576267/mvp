package com.wzf.mvpdemo.http.model.response;


import java.io.Serializable;

public class ShopWalletFlowResponseDto implements Serializable {

    private static final long serialVersionUID = -2711957976975735750L;

    private long id;

    private long shopId;

    private long orderId;

    private long money;

    private int type;

    private int source;

    private String remark;

    private String flowNum;

    private long createTime;

    private boolean isDel;

    private int beforeBalance = 0;

    private int afterBalance = 0;

    /**
     * 1.支付宝 2.微信
     */
    private int payType;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getShopId() {
        return shopId;
    }

    public void setShopId(long shopId) {
        this.shopId = shopId;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getFlowNum() {
        return flowNum;
    }

    public void setFlowNum(String flowNum) {
        this.flowNum = flowNum;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public boolean isDel() {
        return isDel;
    }

    public void setDel(boolean isDel) {
        this.isDel = isDel;
    }

    public int getBeforeBalance() {
        return beforeBalance;
    }

    public void setBeforeBalance(int beforeBalance) {
        this.beforeBalance = beforeBalance;
    }

    public int getAfterBalance() {
        return afterBalance;
    }

    public void setAfterBalance(int afterBalance) {
        this.afterBalance = afterBalance;
    }

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }


    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }
}
