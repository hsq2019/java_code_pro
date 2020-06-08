public class SignalTest {
    private static int SUM;

    //有面包师傅和消费者
    //面包师傅一次造3个面包，消费者每次买一个面包
    //仓库中最多存储100个面包，之后面包师傅就等待不做
    //当仓库中面包个数是0的话，消费者买不了面包了
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            //面包师傅
            new Thread(() -> {
                try {
                    while (true) {
                        synchronized (SignalTest.class) {
                            if (SUM + 3 > 100) {
                                SignalTest.class.wait();
                            } else {
                                SUM += 3;
                                System.out.println(Thread.currentThread().getName() + "生产面包,库存是" + SUM);
                                SignalTest.class.notify();//随机通知一个wait方法阻塞的线程
                                //   SignalTest.class.notifyAll();//通知全部wait方法阻塞的线程,建议写这个

                            }
                        }
                        Thread.sleep(200);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "面包师傅" + i).start();
        }

        for (int i = 0; i < 20; i++) {
            //消费者
            new Thread(() -> {
                try {
                    while (true) {
                        synchronized (SignalTest.class) {
                            if (SUM == 0) {
                                SignalTest.class.wait();
                            } else {
                                SUM--;
                                System.out.println(Thread.currentThread().getName() + "买了面包,库存" + SUM);
                                SignalTest.class.notify();
                                //SignalTest.class.notifyAll();
                            }
                        }
                        Thread.sleep(200);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "消费者" + i).start();
        }
    }
}
