import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;
//线程池的使用例子

public class ThreadPoolTest {
    //创建固定大小的线程池
    private static ExecutorService FIXED_POOL= Executors.newFixedThreadPool(4);

    //创建计划线程池(定时任务)
    private static ScheduledExecutorService SCHEDULED_POOL=Executors.newScheduledThreadPool(4);

    public static void runFixedThreadPool(Runnable task){
        FIXED_POOL.execute(task);
    }
    public static void main(String[] args) {
//        runFixedThreadPool(()->{
//            System.out.println("送快递到北京");
//        });
//        runFixedThreadPool(()->{
//            System.out.println("送快递到上海");
//        });
//        System.out.println("干自己的活");

        /**
         * 第一个参数是传入runnable
         * 第二个参数是延迟多少秒
         * 第三个参数是间隔多少秒
         * 第四个参数，时间单位是秒
         * 延迟多少秒之后执行一次，再每间隔多少执行一次
         */
        SCHEDULED_POOL.scheduleAtFixedRate(()->{
            Date d=new Date();
            DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.println(df.format(d));
        },1,1, TimeUnit.SECONDS);
    }
}
