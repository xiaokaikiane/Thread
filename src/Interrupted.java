public class Interrupted {
    public static void main(String[] args)throws InterruptedException {
//              Thread thread=new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    //调用sleep(),wait(),join()方法时,线程进入阻塞状态
//                    //此时也是可以中断的,中断后抛出InterruptedException
//                    Thread.sleep(5000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        thread.start();
//        //优势在于可以中断wait(),join(),sleep()的阻塞线程
//        thread.interrupt();

        Thread thread1=new Thread(new Runnable() {
            @Override
            public void run() {
               for(int i=0;i<10;i++){
                   // boolean tmp=标志位;
                   //标志位=false
                   //return tmp
                   //作用:重置标志位为false,并且返回之前的标志位
//                   boolean b=Thread.interrupted();
                   //获取当前标志位
                   boolean b=Thread.currentThread().isInterrupted();
                   System.out.println(b);
               }
            }
        });//线程创建之后的标志位为false
        thread1.start();
        thread1.interrupt();//中断线程  //修改标志位为true
    }
}
