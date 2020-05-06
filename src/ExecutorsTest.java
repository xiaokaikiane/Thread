import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorsTest {
   // private static  final ExecutorService EXE=Executors.newSingleThreadExecutor();
    //private static  final ExecutorService EXE=Executors.newFixedThreadPool(5);
    private static final ExecutorService EXE= Executors.newCachedThreadPool();

    public static void main(String[] args) {
        for(int i=0;i<100;i++){
            final int j=i;
            EXE.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println(j);
                }
            });
        }
    }
}
