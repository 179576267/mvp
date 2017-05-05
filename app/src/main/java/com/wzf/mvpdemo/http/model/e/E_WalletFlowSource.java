package com.wzf.mvpdemo.http.model.e;

public enum E_WalletFlowSource implements BaseEnum<Integer>{

    /**
     * 用户结账
     */
    SOURCE_USER_PAY_ORDER(1,"用户买单"),

    SOURCE_SHOP_EXTRACT(2,"商家申请提现"),

    SOURCE_SHOP_EXTRACT_REJECT(98,"提现被拒绝-退回款项"),

    SOURCE_SHOP_EXTRACT_REFUND(99,"提现失败-退回款项")

    ;

    int code;
    String msg;

    E_WalletFlowSource(int code, String msg) {

        this.code = code;
        this.msg = msg;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

}
