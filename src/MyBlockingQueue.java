public class MyBlockingQueue<E> {
    private Object[] elements;//对列元素
    private int addIndex;//添加元素下标
    private int takeIndex;//所取元素下标
    private int size;//队列大小
    public MyBlockingQueue(int capacity){
        elements=new Object[capacity];
    }
    public synchronized E poll(){
        E element= null;
        try {
            while(size==0){
                wait();
            }
            element = (E)elements[takeIndex];
            takeIndex=(takeIndex+1)%elements.length;
            size--;
            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return element;
    }
    public synchronized void offer(E element){
        try {
            while(size==elements.length){
                wait();
            }
            elements[addIndex]=element;
            addIndex=(addIndex+1)%elements.length;
            size++;
            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
/*
    public static void main(String[] args) {
        MyBlockingQueue<Integer>
        for (int i = 0; i <100 ; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {

                }
            })
        }
    }
    */
}
