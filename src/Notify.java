public class Notify {
    public static volatile int COUNT;
    public synchronized static void produce(){
        COUNT+=3;
    }
    public synchronized static void consume(){
        COUNT--;
    }
    public static void main(String[] args) {
        for (int i=0;i<3;i++){ //生产
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        for(int j=0;j<10;j++){
                            synchronized (Notify.class){
                                while(COUNT+3>100){
                                        Notify.class.wait();
                                }
                                produce();
                                System.out.println(Thread.currentThread().getName()+
                                        "生产,库存总量为:"+COUNT);
                                Thread.sleep(1000);
                                Notify.class.notifyAll();
                            }
                            Thread.sleep(1000);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        for (int i=0;i<10;i++){//消费
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while(true){
                            synchronized (Notify.class){
                                if(COUNT==0){
                                    Notify.class.wait();
                                }
                                consume();
                                System.out.println(Thread.currentThread().getName()+
                                        "消费,库存总量为:"+COUNT);
                                Thread.sleep(1000);
                                Notify.class.notifyAll();
                            }
                            Thread.sleep(1000);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
