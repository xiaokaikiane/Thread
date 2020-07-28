package 哈哈哈;

import java.util.concurrent.CountDownLatch;

public class Demo {
    public static void main(String[] args) throws Exception {
        CountDownLatch latch = new CountDownLatch(10);
        Runnable r = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(Math.round(Math.random() * 10000));
                    latch.countDown();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        for (int i = 0; i < 10; i++) {
            new Thread(r).start();
        }
// 必须等到 10 人全部回来
        latch.await();
        System.out.println("结束");
    }
}