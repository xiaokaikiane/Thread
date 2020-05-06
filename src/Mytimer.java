public class Mytimer {
    public void schedule(Runnable task,long delay,long period){
        try {
            long next=System.currentTimeMillis()+delay;
            while(true){
                long current=System.currentTimeMillis();
                if(next>current){
                    Thread.sleep(next-current);
                }
                new Thread(task).start();
                if(period>0){
                    next+=period;
                }else{
                    break;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {

    }
}
