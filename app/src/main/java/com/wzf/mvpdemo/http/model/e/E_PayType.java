package com.wzf.mvpdemo.http.model.e;

/**
 * @author: Gavin
 * Description:
 * Date: 2017/3/1 Time: 16:52
 * Huan Yu Copyright (c) 2016 All Rights Reserved.
 */
public enum E_PayType implements BaseEnum<Integer> {
    UNPAID(0, "未支付"),

    ALIPAY(1, "支付宝"),

    TENPAY(2, "微信"),

    CARD(3, "银行卡"),

    CASH(4,"现金"),

    WALLET(5,"钱包");




    E_PayType(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private int code;

    private String msg;

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
