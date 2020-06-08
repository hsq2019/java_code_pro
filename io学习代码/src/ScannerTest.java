import java.util.Scanner;

public class ScannerTest {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        //io流只能操作一次，hasNest***不进行实际的操作，不操作，只判断
        while (scanner.hasNextInt()){//进行了判断，没有真实的读
            //hasNext***和next***读取道io数据直到满足条件，否则阻塞等待
            int num=scanner.nextInt();//真实的读了
            for(int i=0;i<num;i++){
                int data=scanner.nextInt();
                System.out.println(data);
            }
        }
    }
}
