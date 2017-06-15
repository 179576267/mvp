package com.wzf.mvpdemo.utils.handlerSystem;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description:
 * @author: wangzhenfei
 * @date: 2017-06-14 18:06
 */

public class MessageQueue {
    private final boolean PLAN_A = false;
    private static final int MAX_MESSAGE_COUNT = 10;
    private Message[] msgs;
    private Lock lock = new ReentrantLock();
    private Condition fullLock;
    private Condition emptLock;
    private int setIndex;
    private int getIndex;
    private int count;

    /**
     * 引入一个值为的信号量，防止mList过大
     */
    private volatile Semaphore mSemaphoreAdd;
    private volatile Semaphore mSemaphoreSub;

    public MessageQueue() {
        msgs = new Message[50];
        if (PLAN_A) {
            fullLock = lock.newCondition();
            emptLock = lock.newCondition();
        } else {
            mSemaphoreAdd = new Semaphore(MAX_MESSAGE_COUNT);
            mSemaphoreSub = new Semaphore(MAX_MESSAGE_COUNT);
            try {
                mSemaphoreSub.acquire(MAX_MESSAGE_COUNT); // 全部获取信号量
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void enqueueMessage(Message msg) {
        if (PLAN_A) {
            try {
                lock.lock();
                while (count == msgs.length) {
                    try {
                        fullLock.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                msgs[setIndex] = msg;
                setIndex = (++setIndex == msgs.length) ? 0 : setIndex;

                count++;
                emptLock.signal();
            } finally {
                lock.unlock();
            }
        } else {
            try {
                mSemaphoreAdd.acquire(); // 获得一个信号量，没有的时候阻塞
                msgs[setIndex] = msg;
                setIndex = (++setIndex == msgs.length) ? 0 : setIndex;
                mSemaphoreSub.release();
                count++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public Message next() {
        Message msg = null;
        if (PLAN_A) {
            try {
                lock.lock();
                while (count == 0) {
                    try {
                        emptLock.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                msg = msgs[getIndex];
                getIndex = (++getIndex == msgs.length) ? 0 : getIndex;
                count--;
                fullLock.signal();
            } finally {
                lock.unlock();
            }
        } else {
            try {
                mSemaphoreSub.acquire();
                msg = msgs[getIndex];
                getIndex = (++getIndex == msgs.length) ? 0 : getIndex;
                mSemaphoreAdd.release();// 释放一个信号量
                count--;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return msg;
    }

}
