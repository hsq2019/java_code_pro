 class Singleton{
    //饿汉式
     //线程安全
    public static Singleton SINGLETON=new Singleton();
    private Singleton(){}//构造方法
    public static Singleton getInstance(){
        return SINGLETON;
    }

    //懒汉式
     //线程不安全
    public static Singleton SINGLETON2;
    public static Singleton getInstance2(){
        if(SINGLETON2==null)
            SINGLETON2=new Singleton();
        return SINGLETON2;
    }
    //懒汉式加上Synchronized关键字
    public static Singleton SINGLETON3;
     public static synchronized Singleton getInstance3(){
         if(SINGLETON3==null)
             SINGLETON3=new Singleton();
         return SINGLETON3;
     }
     //双重校验锁的单例模式，线程安全
     public volatile static Singleton SINGLETON4;
     public static  Singleton getInstance4(){
         if(SINGLETON4==null){
             synchronized (Singleton.class){
                 if(SINGLETON4==null){
                     SINGLETON4=new Singleton();
                 }
             }
         }
         return SINGLETON4;
     }
}
public class SingletonDemo{
    public static void main(String[] args) {
        for(int i=0;i<10;i++){
            new Thread(() ->{
                Singleton.getInstance4();
            }).start();
        }
    }

}
