public class CreateThread {
    public static void main(String[] args) {
        //继承Thread类
        Mythread t1 =new Mythread();
        t1.start();
        t1.run();
        //实现Runnable接口
        MyRunner myRunner=new MyRunner();
        Thread t2=new Thread(myRunner);
        t2.start();
        //javamain是java层面的主线程,和main方法自行创建的线程是同级的
        while(true){

        }
    }
    static class Mythread extends Thread{
        public void run(){
            System.out.println("in thread");
            while(true){

            }
        }
    }
    static class MyRunner implements Runnable{

        @Override
        public void run() {
            System.out.println("in thread");
            while(true){

            }
        }
    }
}
