package nuike;


import java.util.Scanner;

public class PrintNumbers {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        String s=scanner.next();
        String res="";
        int count=0;
        char[] arr=s.toCharArray();
        for(int i=0;i<arr.length;i++){
            if(arr[i]>='0'&&arr[i]<'9'){
                count=1;
                int index=i;
                for(int j=i+1;j<arr.length;j++){
                    if(arr[j]>='0'&&arr[i]<'9'){
                        count++;
                        index=j;
                    }else {
                        break;
                    }
                }
                if(count>res.length()){
                    res=s.substring(i,index+1);
                }else{
                    continue;
                }
            }
        }
        System.out.println(res);
    }
}
