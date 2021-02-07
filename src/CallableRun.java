

import java.util.concurrent.*;

public class CallableRun {
    public static void test1()throws ExecutionException,InterruptedException {
        Callable<Integer> callable=new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println(Thread.currentThread().getName()+":call");
                return 1;
            }
        };
        FutureTask<Integer> task=new FutureTask<>(callable);
        new Thread(task).start();
        System.out.println(Thread.currentThread().getName()+":子线程run before get()");
        System.out.println(Thread.currentThread().getName()+" get:"+task.get());
        System.out.println(Thread.currentThread().getName()+":子线程run after get()");
    }
    public static  void test2()throws ExecutionException,InterruptedException{
        ExecutorService pool= Executors.newFixedThreadPool(4);
//        Future future=pool.submit(new Callable<String>() {
//            @Override
//            public String call() throws Exception {
//                System.out.println(Thread.currentThread().getName()+":call");
//                return "call finish";
//            }
//        });
        FutureTask<?> futureTask=new FutureTask<>(new Callable<Object>() {
            @Override
            public String call() throws Exception {
                System.out.println(Thread.currentThread().getName()+":call");
                return "call finish";
            }
        });
        pool.submit(futureTask);
        System.out.println(Thread.currentThread().getName()+":pool submit ,before get()");
        System.out.println(Thread.currentThread().getName()+" get:"+ futureTask.get());
    }
    public static void main(String[] args)throws Exception {
        test1();
        test2();
    }
}
