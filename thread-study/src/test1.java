public class test1 {
    public static void main(String[] args) {
        for(int i=0;i<5;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                }
            }).start();
        }
    }
}
