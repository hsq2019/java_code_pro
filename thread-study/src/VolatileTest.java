public class VolatileTest {
    //Volatile是保证共享变量在内存去读取
    private static volatile int SUM;

    //如果有了volatile关键字修饰，SUM++这个指令再同时具备原子性的话，那么线程就安全了
    public static void increment(int n){
        //1.从主内存中读取到SUM，放入工作内存中
        //2.工作内存中修改SUM
        //3.把SUM写回到主内存中
        SUM++;
    }
    public static void main(String[] args) {
        for(int i=0;i<20;i++){
            new Thread(() ->{
                for(int j=0;j<10000;j++){
                    increment(j);
                }
            }).start();
        }
        System.out.println(SUM);
    }
}
