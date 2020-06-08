public class MultiTreatedFinishThenDoMain {
    //期望结果： 先打印0-19随机同时打印，之后再打印main
    public static void main(String[] args) throws InterruptedException {
        Thread[] threads=new Thread[20];
        for(int i=0;i<20;i++){
            final int j=i;
            Thread t=new Thread(()->{
                System.out.println(j);
            });
            t.start();//线程开始
            threads[i]=t;//把线程放到数组里面
        }
        for(int i=0;i<20;i++){
            threads[i].join();
        }
        System.out.println("main");
    }
}
