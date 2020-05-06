package 哈哈哈;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {
    // 最多 5 个坑
    private static final Semaphore avialable = new Semaphore(5);

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(10);
        Runnable r = new Runnable() {
            public void run() {
                try {
                    avialable.acquire(); //此方法阻塞
                    Thread.sleep(10 * 1000);
                    System.out.println(Thread.currentThread().getName());
                    avialable.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        for (int i = 0; i < 10; i++) {
            pool.execute(r);
        }
        pool.shutdown();
    }
}