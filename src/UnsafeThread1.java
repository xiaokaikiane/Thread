
public class UnsafeThread1 {
    public static int COUNT;

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j=0; j < 10000; j++) {
                        if (COUNT<100000) {
                            synchronized (UnsafeThread1.class) {
                                if (COUNT < 100000) {
                                    COUNT++;
                                }
                            }
                        }
                    }
                }
            });
            thread.start();
        }
        while(Thread.activeCount()>2){
            Thread.yield();
        }
        System.out.println(COUNT);
    }
}
