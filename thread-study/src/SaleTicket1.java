import java.util.Locale;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Ticket{
    private int num=30;
//    public synchronized void sale(){ 同步方法
//        synchronized (this);同步代码块
//    }
    Lock lock=new ReentrantLock();//可重复锁
    public void sale(){
        lock.lock();
        try{
            if(num>0){
                System.out.println(Thread.currentThread().getName()+"卖第"+(num++)+"张票，还剩票数是"+num);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();//解锁
        }
    }

}
public class SaleTicket1 {
//买票
//3个售票员，卖30张票
    public static void main(String[] args) {
        Ticket ticket=new Ticket();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<40;i++){
                    ticket.sale();
                }
            }
        }, "A").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<40;i++){
                    ticket.sale();
                }
            }
        }, "B").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<40;i++){
                    ticket.sale();
                }
            }
        }, "C").start();
    }

}
