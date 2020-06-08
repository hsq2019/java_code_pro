import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

//lambda练习
@FunctionalInterface//函数式
interface Foo{//函数式接口
    public int add(int x,int y);
    //default
    default int div(int x,int y){
        return x/y;
    }
    //static
    static int mul(int x,int y){
        return x*y;
    }
}

public class LambdaTest {
    public static void main(String[] args) {
        //复制小括号，写死右箭头，落地大括号
//    Foo foo=new Foo() { //这个是匿名内部类
//        @Override
//        public int add(int x, int y) {
//            return x+y;
//        }
//    };
        Foo foo=(int x,int y) ->{
            return x+y;
        };
        System.out.println(foo.add(15,5));
        System.out.println(foo.div(15,5));
        System.out.println(Foo.mul(15,5));
    }

}
