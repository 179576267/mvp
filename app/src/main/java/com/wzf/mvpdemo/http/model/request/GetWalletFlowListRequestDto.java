package com.wzf.mvpdemo.http.model.request;

/**
 * @author wangzhenfei
 * 2017-03-17 18:49
 * 获取
 */
public class GetWalletFlowListRequestDto extends PostParam{
    private int  index; // 开始位置
    private int  type;  //（1：进账；2：出账）

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }



    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "GetWalletFlowListDto{" +
                ", index=" + index +
                ", type=" + type +
                '}';
    }
}
