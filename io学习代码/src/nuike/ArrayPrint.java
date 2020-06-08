package nuike;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// test
public class ArrayPrint {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String s=sc.nextLine();
        String[] arr=s.split(" ");
        int[] a=new int[arr.length];
        for(int i=0;i<arr.length;i++){
            a[i]=Integer.valueOf(arr[i]);
        }
        HashMap<Integer,Integer> hashMap=new HashMap<>();
        for(int i=0;i<a.length;i++){
            hashMap.put(a[i],hashMap.getOrDefault(a[i],0)+1);
        }
        for (Map.Entry<Integer, Integer> entry : hashMap.entrySet()) {
            if(entry.getValue()>=(int)a.length/2){
                System.out.println(entry.getKey());
            }
        }
    }
}
