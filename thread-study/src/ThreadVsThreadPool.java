import java.util.concurrent.*;

public class ThreadVsThreadPool {
    //线程池的使用
    public static void main(String[] args) {
        /**
         * 1，没有使用线程，送快递
         * 2，使用手动创建线程，送快递
         * 3，使用jdk的线程池，送快递
         */

        //1，自己送快递，同时自己还干自己的事情
        System.out.println("快递到北京");
        System.out.println("快递到上海");
        System.out.println("处理自己的业务");
        //2,雇佣了两个人送快递，同时我自己干自己的事情
        new Thread(() ->{ System.out.println("快递到北京"); }).start();
        new Thread(() ->{ System.out.println("快递到上海"); }).start();
         System.out.println("处理自己的业务");
        //3，jdk
        //ctrl+鼠标左键可以查看
        //创建线程池对象，相当于开了家快递公司，专门处理送快递任务
        ThreadPoolExecutor pool=new ThreadPoolExecutor(
                4,//核心线程数，相当于快递公司的正式员工-----线程
                10,//最大线程数，总员工(正式工+临时工)-----线程
                //临时工+空闲时间：正式员工数量不够的时候，就招聘临时工
                //空闲时间60s：临时工60s 不干活，就会被解雇
                60,//空闲时间数
                TimeUnit.SECONDS,//时间单位
                new ArrayBlockingQueue<Runnable>(1000),//阻塞队列，快递公司的仓库，存放快递----存放线程的容器
                new ThreadFactory() {//匿名内部类，创建线程的工厂类：快递公司的招聘标准-----创建线程的方式
                    @Override
                    public Thread newThread(Runnable r) {
                        return new Thread(r);
                    }
                },
                //拒绝策略：接收到了新的快递单，但是仓库容量不够存放包裹，那么就要拒绝
                //new ThreadPoolExecutor.AbortPolicy() 抛异常方式 RejectedExecutionException
                //new ThreadPoolExecutor.CallerRunsPolicy() 谁把包裹给我的，让他自己送去(execute代码所在线程自己执行)
                //new ThreadPoolExecutor.DiscardOldestPolicy() 把仓库中的旧包裹丢弃扔掉
                new ThreadPoolExecutor.AbortPolicy()//把仓库中的最新包裹丢弃扔掉
        );
        //execute()方法是快递公司接收快递的接口
        pool.execute(()->{//创建了(runnable)一个送快递的任务，把包裹交给(execute)快递公司
            //runnable----快递包裹
            System.out.println("快递送到北京");
        });
        pool.execute(()->{
            System.out.println("快递送到上海");
        });
        System.out.println("处理自己的事情");

        //单线程池：只有一个正式工，没有临时工，仓库无边界
        ExecutorService pool2=Executors.newSingleThreadExecutor();
        //固定大小的线程池：只给定数量的正式工，没有临时工，仓库有是无边界的
        ExecutorService pool3=Executors.newFixedThreadPool(4);
        //计划任务线程池：给定数量的正式工，没有临时工。使用自己创建的线程方式（定时任务线程）
        ExecutorService pool4=Executors.newScheduledThreadPool(4);
        //缓存的线程池：只有临时工，没有正式工，临时工数量不限，空闲时间60s
        ExecutorService pool5=Executors.newCachedThreadPool();

    }
}
