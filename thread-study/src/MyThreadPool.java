import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

//创建快递公司
public class MyThreadPool {

   //仓库
    private BlockingQueue<Runnable> queue;//阻塞队列

    /**
     *
     * @param corePoolSize 员工数量
     * @param capacity 员工最大数量
     */
    //构造方法
    public void MyThreadPool(int corePoolSize,int capacity){
        queue=new ArrayBlockingQueue<>(capacity);
        for(int i=0;i<corePoolSize;i++){
            new MyThread(queue).start();
        }

    }

    //把包裹放进仓库中
    //快递公司开放出来送快递的接口(电话，营业点),客户调用接口去实现把包裹放进仓库中
    public void execute(Runnable task){
        try {
            queue.put(task);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //创建员工
    private static class MyThread extends Thread{
        private BlockingQueue<Runnable> queue;

        public MyThread(BlockingQueue<Runnable> queue) {
            this.queue=queue;
        }

        //员工不停的在仓库取包裹，如果没取到旧阻塞等待，取到了就执行
        public void run(){
            try {
                while (true){
                    Runnable task=queue.take();
                    task.run();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
