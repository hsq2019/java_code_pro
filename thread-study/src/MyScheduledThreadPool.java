import java.util.Date;
import java.util.PriorityQueue;
import java.util.concurrent.PriorityBlockingQueue;


public class MyScheduledThreadPool {
    //自己创建计划线程池

    //员工
    private MyTimer[] threads;
    //仓库，阻塞队列
    private PriorityBlockingQueue<MyTimerTask> queue=new PriorityBlockingQueue<>();
    public MyScheduledThreadPool(int capacity){
        threads =new MyTimer[capacity];
        for(int i=0;i<capacity;i++){
            threads[i]=new MyTimer(queue);
            threads[i].start();
        }
    }
    //执行定时任务

    /**
     * 执行定时任务
     * @param runnable 任务
     * @param delay 延迟多少秒
     * @param period 间隔多少秒
     */
    public void schedule(Runnable runnable,long delay,long period){
        MyTimerTask task=new MyTimerTask(runnable,new Date().getTime()+delay,period);
        synchronized (queue) {
            queue.put(task);
            queue.notifyAll();
        }
    }


    //员工
    private static class MyTimer extends Thread {
        private PriorityBlockingQueue<MyTimerTask> queue;

        public MyTimer(PriorityBlockingQueue<MyTimerTask> queue) {
            this.queue = queue;
        }

        public void run() {
            while (true) {
                try {
                    //take/put 方法在达到上限和下限时候，是阻塞等待
                    //poll/offer是非阻塞方法，达到上限或者下线的时候，返回空
                    MyTimerTask task = queue.take();
                    synchronized (queue) {
                        long current = System.currentTimeMillis();
                        if (current < task.next) {
                            queue.wait(task.next - current);
                            //等待直到满足下次执行时间，需要从仓库重新去包裹
                            //可能有时间更紧急的包裹需要派送
                            //之前从仓库取出来了，要重新放回去，此处没有执行
                            queue.put(task);

                        } else {
                            task.run();
                            if (task.period > 0) {
                                //下次执行时间修改成当前的next+period
                                task.next = task.next + task.period;
                                queue.put(task);
                            }
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }
    //定时任务有
    private static class MyTimerTask implements Runnable,Comparable<MyTimerTask>{
        private Runnable runnable;
        private long next;
        private long period;
        public MyTimerTask(Runnable runnable,long next,long period){
            this.next=next;
            this.period=period;
            this.runnable=runnable;
        }

        @Override
        public void run() {
            runnable.run();
        }

        @Override
        public int compareTo(MyTimerTask o) {

            return Long.compare(next,o.next);
        }
    }

    public static void main(String[] args) {
        MyScheduledThreadPool pool= new MyScheduledThreadPool(4);
        pool.schedule(()->{
            System.out.println("abc");
        },0,1000);
    }
}
