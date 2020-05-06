public class Sequenceprint {
    public volatile static String INDEX;
    public static void main(String[] args) {
        Thread a=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i <10 ; i++) {
                        synchronized (Sequenceprint.class){
                            while(!INDEX.equals("A")){
                                Sequenceprint.class.wait();
                            }
                            System.out.println(INDEX);
                            INDEX="B";
                            Sequenceprint.class.wait();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        a.start();
    }
}
