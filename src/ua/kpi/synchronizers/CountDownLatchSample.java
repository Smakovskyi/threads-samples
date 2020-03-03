package ua.kpi.synchronizers;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;

public class CountDownLatchSample {
    static CountDownLatch countDownLatch = new CountDownLatch(1);
    static class Waiter extends Thread{
        @Override
        public void run() {
            System.out.println("waiter started");
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("waiter finished");
        }
    }
    public static void main(String[] args) throws Exception{
        new Waiter().start();
        Thread.sleep(2000);
        countDownLatch.countDown();

    }
}
