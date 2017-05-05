package com.wzf.mvpdemo.http.model.request;

public class BaseParamDto {

    private BaseParam baseParam;

    public BaseParam getBaseParam() {
        return baseParam;
    }

    public void setBaseParam(BaseParam baseParam) {
        this.baseParam = baseParam;
    }

    @Override
    public String toString()
    {
        return "{" +
                "baseParam=" + baseParam +
                '}';
    }
}
