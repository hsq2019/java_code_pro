public class one {
    public static void main(String[] args) throws InterruptedException {
        String name = null;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(999999999999L);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        },name="good").start();
    }
}
