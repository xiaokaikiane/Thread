import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class ThreadAdvantage {
    public static List<String> randomList(){
        char[] chars={'a','b','c','d','e','f'};
        List<String> list=new LinkedList<>();
        for(int i=0;i<100000;i++){
            int random=new Random().nextInt(chars.length);
            char c=chars[random];
            list.add(String.valueOf(c));
        }
        return list;
    }
    public static void main(String[] args)throws Exception {
      List<String> list=randomList();
      long start=System.currentTimeMillis();//获取起始时间
        Thread[] threads=new Thread[10];
      for(int i=0;i<10;i++){
          final int k=i;
          final Thread thread = new Thread(new Runnable() {
              @Override
              public void run() {
                  for (int j = 0; j < 10000; j++) {
                      list.get(k * 10000 + j);
                  }
              }
          });
          threads[i].start();

      }
      /**
      while(Thread.activeCount()>2){  //线程让步
          Thread.yield();
        }
         */for(Thread thread:threads){
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
      long end=System.currentTimeMillis();//结束时间
        System.out.println("耗时:"+(end-start)+"毫秒");
     }
}
