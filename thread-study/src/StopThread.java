//中断线程，停止线程
public class StopThread {
    private volatile static boolean IS_STOP;

    public static void main(String[] args) throws InterruptedException {

        new Thread(() ->{
            try {
                while (!IS_STOP){
                    System.out.println(Thread.currentThread().getName());
                    Thread.sleep(1000L);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        Thread.sleep(2000L);
        IS_STOP=true;

    }

}
