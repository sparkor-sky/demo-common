package latch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class Latch {

    public static void main(String[] args) throws InterruptedException{
        timeTasks(5, new Runnable() {
            @Override
            public void run() {
                Random random = new Random();
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread random:" + random.nextInt());
            }
        });
    }

    public static void timeTasks(int nThreads, final Runnable task)
            throws InterruptedException {
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(nThreads);

        for (int i = 0; i < nThreads; i++) {
            Thread t = new Thread(() -> {
                try {
                    startGate.await();
                    task.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    endGate.countDown();
                }

            });
            t.start();
        }

        long start = System.nanoTime();
        startGate.countDown();
        endGate.await();
        long end = System.nanoTime();
        System.out.println(end - start);
    }
}
