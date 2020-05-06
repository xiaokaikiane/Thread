

public class Sequense {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("哈哈哈哈啊哈哈");
            }
        }).start();
        //下面代码先执行,因为上面创建线程耗时长

        System.out.println("嘻嘻嘻嘻嘻嘻嘻嘻嘻");
        /**
        for(int i=0;i<10;i++) {
            final int j=i;
            Runnable r = new Runnable(){
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000 * j);
                        System.out.println(j);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            };
            Thread t=new Thread(r);
            t.start();
        }
         */
    }
}
