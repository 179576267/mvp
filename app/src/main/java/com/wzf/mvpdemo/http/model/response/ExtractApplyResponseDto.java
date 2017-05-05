package com.wzf.mvpdemo.http.model.response;

/**
 * @author wangzhenfei
 *         2017-03-23 15:29
 */
public class ExtractApplyResponseDto{
    private int afterBalacnee;
    private long confirmTime;

    public int getAfterBalacnee() {
        return afterBalacnee;
    }

    public void setAfterBalacnee(int afterBalacnee) {
        this.afterBalacnee = afterBalacnee;
    }

    public long getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(long confirmTime) {
        this.confirmTime = confirmTime;
    }
}
