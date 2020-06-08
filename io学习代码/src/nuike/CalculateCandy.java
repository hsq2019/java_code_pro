package nuike;

import java.util.Scanner;

public class CalculateCandy {

    //A-B=arr[0]
    //B-C=arr[1]
    //A+B=arr[2]
    //B+C=arr[3]
    public static void main(String[] args) {

        Scanner scanner=new Scanner(System.in);
        int[] arr=new int[4];
            for(int i=0;i<4;i++){
                arr[i]=scanner.nextInt();
            }
        int B=(arr[2]-arr[0])/2;
        int A=arr[0]+B;
        int C=arr[3]-B;
        if(help(A,B,C,arr)){
            System.out.println(A+" "+B+" "+C);
        }else{
            System.out.println("NO");
        }
    }
    private static boolean help(int a,int b,int c,int[] arr){
        if((a-b==arr[0])&&(b-c==arr[1])&&(a+b==arr[2])&&(b+c==arr[3])){
            return true;
        }
        return false;
    }
}
