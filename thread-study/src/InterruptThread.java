public class InterruptThread {
    //线程中断是使用中断标志位来实现的，而不是让一个线程真的推出
    //interrupt只是通知，而不是真的就中断了，中部中断是由自己写的代码决定
    public static void main(String[] args) throws InterruptedException {

        //1.在子线程sleep阻塞的时候，中断它
                Thread t=new Thread(() ->{
            try {
                while (!Thread.interrupted()){//判断线程是否被中断
                    System.out.println(Thread.currentThread().getName());
                    Thread.sleep(1000000L);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t.start();
        Thread.sleep(2000L);
        t.interrupt();//中断线程

//        Thread t=new Thread(() ->{//中断是用抛出异常的方式进行中断，中断完成之后中断标志位会重置，所以还是会一直循环
//                while (!Thread.interrupted()){
//                    try {//阻塞可以通过Interrupt去中断
//                        System.out.println(Thread.currentThread().getName());
//                        Thread.sleep(3000L);
//                    } catch (InterruptedException e) {//抛出异常以后，interrupted标志位重置
//                        e.printStackTrace();
//                    }
//                }
//            });
//        t.start();
//        Thread.sleep(2000L);
//       t.interrupt();

//        Thread t=new Thread(() ->{
//            //这个是因为interrupted会重置类似是这个代码
//            //boolean temp=interrupted;
//            //interrupted=false;
//            //return temp;
//            for(int i=0;i<10;i++){
//                System.out.println(Thread.interrupted());//这个会重置标志位打印的结果是第一个是true，其余9个都是false
                  //System.out.println(Thread.currentThread().isInterrupted());//这个不会重置标志位
//            }
//        });
//        t.start();
//        t.interrupt();
    }
}
