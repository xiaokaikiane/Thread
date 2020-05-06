import java.util.ArrayList;
import java.util.List;

public class UnsafeThread {
    public static int COUNT;

    public static void main(String[] args) {
        List<Integer> list=new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            final int N=i;
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j=0; j < 10000; j++) {
                      //  COUNT++;
                        list.add(N*10000+j);
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
