package com.wzf.mvpdemo.utils.handlerSystem;

import android.os.*;

/**
 * @Description:
 * @author: wangzhenfei
 * @date: 2017-06-14 18:06
 */

public class Message {
    public int what;
    public Object obj;
    Handler target;

    public Message(int what, Object obj) {
        this.what = what;
        this.obj = obj;
    }

    @Override
    public String toString() {
        return "Message{" +
                "what=" + what +
                ", obj=" + obj +
                '}';
    }
}
