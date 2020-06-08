/**
 * 阻塞队列
 * 取元素，队列是空，阻塞等待
 * 如果队列满了，村元素的时候，阻塞等待
 */

public class MyBlockingQueue<T> {//T是泛型
    private Object[] table;//take返回的时候，用强制类型转换就行

    private int takeIndex;//取出的位置
    private int putIndex;//存进去的位置
    private int size;//数组生成的队列中的元素多少（面包库存多少）

    public MyBlockingQueue(int capacity){
        table=new Object[capacity];
    }

    public synchronized void put(T element) throws InterruptedException {
        while (size==table.length)
            wait();
        table[putIndex]=element;
        putIndex=(putIndex+1)%table.length;
        size++;
        notifyAll();
    }
    public synchronized T take() throws InterruptedException {
        while (size==0)
            wait();
        Object element=table[takeIndex];
        takeIndex=(takeIndex+1)%table.length;
        size--;
        notifyAll();
        return (T)element;
    }

    //不可以使用volatile不加锁的方式完成size
    //可能导致put/
    public synchronized int size(){
        return size;
    }

    //模拟使用自定义的阻塞队列
    public static void main(String[] args) {
        MyBlockingQueue<Integer> queue=new MyBlockingQueue(100);
        for(int i=0;i<5;i++){
            new Thread(() ->{
                //模拟生产面包,一次生产1个
                try {
                    while (true){
                        synchronized (queue) {
                            queue.put(1);
                            //打印库存数量，是存在信息不一致的问题
                            //使用不当：size是希望获取put方法，调用并且执行完放元素操作时的size
                            //两端代码是有原子性需求的，但是没有保证原子性，会出现问题
                            //解决方法是加锁
                            System.out.println("存面包+1"+queue.size());
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        for(int i=0;i<20;i++){
            new Thread(() ->{
                //模拟消费者
                try {
                    while (true){
                        synchronized (queue){
                            Integer e=queue.take();
                            System.out.println("消费面包-1"+queue.size());
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

}
