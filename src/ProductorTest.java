/**
 * 生产者消费者问题
 * 分析：
 *  1.是否是多线程的问题  生产者线程   消费者线程
 *  2.是否共享数据  是，店员
 *  3.是否涉及线程安全 是
 *  4.是否涉及线程通信  是
 */
class Clerk{//店员,产品
    private int productCount=0;
    public synchronized void produce() {//生产产品
        if(productCount<20){
            productCount++;
            System.out.println(Thread.currentThread().getName()+":开始生产第"+productCount+"个产品");
            notify();//只要生产的有产品,就可以唤醒消费
        }else{
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void custom() {//消费产品
        if(productCount>0){
            System.out.println(Thread.currentThread().getName()+"开始消费第"+productCount+"个产品");
            productCount--;
            notify();//只要消费了,就可以唤醒生产
        }else{
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
class Productor extends Thread{//生产者
private Clerk clerk;

    public Productor(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        System.out.println(getName()+":开始生产产品....");
        while(true){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.produce();
        }
    }
}
class Customer extends Thread{//消费者
   private Clerk clerk;

    public Customer(Clerk clerk) {
        this.clerk = clerk;
    }
    @Override
    public void run() {
        System.out.println(getName()+":开始消费产品....");
        while(true){
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.custom();
        }
    }
}
public class ProductorTest {
    public static void main(String[] args) {
        Clerk clerk=new Clerk();
        Productor p1=new Productor(clerk);
        p1.setName("生产者1");

        Customer c1=new Customer(clerk);
        c1.setName("消费者1");
        p1.start();
        c1.start();
    }
}
