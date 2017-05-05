package com.wzf.mvpdemo.http.model.request;

/**
 * Created by kjg.
 */
public class SetPasswordParam extends PostParam {

    private String oldPayPass;
    private String newPayPass;

    public String getOldPayPass() {
        return oldPayPass;
    }

    public void setOldPayPass(String oldPayPass) {
        this.oldPayPass = oldPayPass;
    }

    public String getNewPayPass() {
        return newPayPass;
    }

    public void setNewPayPass(String newPayPass) {
        this.newPayPass = newPayPass;
    }
}
