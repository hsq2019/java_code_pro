package nuike;

import java.util.Scanner;

//买苹果
//6个一袋，8个一带
public class BuyApple {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while (scanner.hasNextInt()){
            int n=scanner.nextInt();
            System.out.println(buy(n));
        }



    }
    public static int buy(int n){
        if(n%2!=0||n==10||n<6){
            return -1;
        }
        if(n%8==0){
            return n/8;
        }
        return 1+n/8;
    }
}
