package com.wzf.mvpdemo.utils.handlerSystem;

/**
 * @Description:
 * @author: wangzhenfei
 * @date: 2017-06-14 18:06
 */

public class Handler {
    private Looper mLooper;
    private MessageQueue mQueue;
    public Handler() {
        this.mLooper = Looper.myLooper();
        this.mQueue = mLooper.mQueue;

    }

    public void sendMessage(Message msg){
        System.out.println("sendMessage:" + msg);
        msg.target = this;
        mQueue.enqueueMessage(msg);
    }

    /**
     * Subclasses must implement this to receive messages.
     */
    public void handleMessage(Message msg) {
    }

    /**
     * Handle system messages here.
     */
    public void dispatchMessage(Message msg) {
        handleMessage(msg);
    }


}
