package com.wzf.mvpdemo.utils.handlerSystem;

/**
 * @Description:
 * @author: wangzhenfei
 * @date: 2017-06-14 18:06
 */

public class Looper {
    static final ThreadLocal<Looper>  sThreadLocal = new ThreadLocal<>();
    public MessageQueue mQueue;

    public Looper() {
        mQueue = new MessageQueue();
    }

    public static void prepare(){
        if (sThreadLocal.get() != null) {
            throw new RuntimeException("Only one Looper may be created per thread");
        }
        sThreadLocal.set(new Looper());
    }

    public static Looper myLooper() {
        return sThreadLocal.get();
    }

    public  static void loop() {
        final Looper me = myLooper();
        if(me == null){
            throw new RuntimeException("No Looper; Looper.prepare() wasn't called on this thread.");
        }
        MessageQueue mQueue = me.mQueue;
        for(;;){
            Message msg = mQueue.next();
            if(msg == null){
                continue;
            }
            msg.target.dispatchMessage(msg);
        }
    }
}
