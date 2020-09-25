package com.kyanite.ft.toolbox;

public class TestSyn implements Runnable {
    public static final Object lockHelper = new Object();

    static int count = 0;

    public synchronized void increase() {
        for (int i = 0; i < 10000; i++) {
            count++;
        }
    }

    @Override
    public void run() {
        increase();
    }

    public static void main(String[] args) throws InterruptedException {
        TestSyn ts = new TestSyn();
        Thread t1 = new Thread(ts);
        Thread t2 = new Thread(ts);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(count);
    }
}
