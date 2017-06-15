package com.wzf.mvpdemo.utils.handlerSystem;

/**
 * @Description:
 * @author: wangzhenfei
 * @date: 2017-06-14 18:07
 */

public class HandlerTest {
    static Handler handler;
    public static void main(String[] args) {
        Looper.prepare();
       handler = new Handler(){
           private int count;
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                System.out.println("-------------------------------------->>>" + msg + " count:" + ++count );
            }
        };
        for(int i = 0 ; i < 5 ; i++){
            sendMessage(i);
        }

        Looper.loop();
        System.out.println("the end!");
    }

    private static void sendMessage(final int j) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0 ;i < 10; i++ ){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    handler.sendMessage(new Message(j * 10000 + i, i + ""));
                }


            }
        }).start();
    }
}
