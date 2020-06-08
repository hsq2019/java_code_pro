public class JoinTest {
    public static void main(String[] args) throws InterruptedException {
        Thread t=new Thread(()->{
            System.out.println("Thread baby");
        });
        t.start();
        t.join();//等待t执行完毕再往下执行
        System.out.println("main");
    }
}
