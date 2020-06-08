package nuike;

import java.util.Scanner;
import java.util.Stack;

public class MagicPocket {
    static int count=0;
    static int[] arr;
    static int n;

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        n=sc.nextInt();
        arr=new int[n+1];
        for(int i=1;i<n+1;i++){
            arr[i]=sc.nextInt();
        }
       help(40,n);
        System.out.println(count);
        Stack<Integer> stack=new Stack<>();
        stack.isEmpty();
    }
    public static void help(int s,int n){

        if(s==0){//背包容量刚好是0，装满了
            count++;
        }
        if(s<=0||(s>0&&n<0)){//不能刚好装满
            return;
        }
        help(s-arr[n],n-1);
        help(s,n-1);
    }
}
