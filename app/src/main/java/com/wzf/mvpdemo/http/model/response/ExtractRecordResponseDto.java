package com.wzf.mvpdemo.http.model.response;

/**
 * Created by bean on 2017/3/16.
 */
public class ExtractRecordResponseDto {

    private long id;

    private long shopId;

    private String extractTradeNo;

    private int money;

    private int type;

    private String account;

    private String accountName;

    private String remark;

    private int status;

    private long createTime;

    private long confirmTime;

    private long disposeTime;

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

    public String getExtractTradeNo() {
        return extractTradeNo;
    }

    public void setExtractTradeNo(String extractTradeNo) {
        this.extractTradeNo = extractTradeNo;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(long confirmTime) {
        this.confirmTime = confirmTime;
    }

    public long getDisposeTime() {
        return disposeTime;
    }

    public void setDisposeTime(long disposeTime) {
        this.disposeTime = disposeTime;
    }
}
