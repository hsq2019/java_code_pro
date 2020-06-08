public class SequencePrint {

    /**
     * 1.三个线程
     * 第一个只能打印A，第二个只能打印B，第三个只能打印C
     * 三个线程同时执行，打印结果是ABC
     * 2.升级版要求
     * 同时执行
     * 打印结果 ABC循环10次
     */
    private static class Print implements Runnable{//内部类
        private String content;
        private Thread t;
        public Print(String content,Thread t){
            this.content=content;
            this.t=t;
        }
        @Override
        public void run() {

                try {
                    if(t!=null){
                        t.join();
                    }
                    System.out.println(content);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


        }


    }
    public static void print1(){
        Thread t1=new Thread(new Print("A",null));
        Thread t2=new Thread(new Print("B",t1));
        Thread t3=new Thread(new Print("C",t2));
        t1.start();
        t2.start();
        t3.start();
    }

    public static void main(String[] args) throws InterruptedException {
        print1();


    }
}
