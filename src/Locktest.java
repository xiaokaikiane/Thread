import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Locktest {
    private static int sum;
    public static void test1(){
        for (int i=0;i<20;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int j=0;j<10000;j++){
                        synchronized (Locktest.class){
                            sum++;
                        }
                    }
                }
            }).start();
        }
        while(Thread.activeCount()>2){
            Thread.yield();
        }
        System.out.println(sum);
    }
    public static void test2(){
        Lock lock=new ReentrantLock();
        for (int i=0;i<20;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int j=0;j<10000;j++){
                        try {
                            lock.lock();
                            sum++;
                        } finally {
                            lock.unlock();
                        }
                    }
                }
            }).start();
        }
        while(Thread.activeCount()>2){
            Thread.yield();
        }
        System.out.println(sum);
    }
    public static void main(String[] args) {
        test2();
    }
}
