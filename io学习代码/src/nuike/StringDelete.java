package nuike;

import java.util.Scanner;

public class StringDelete {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        while (sc.hasNextLine()){
            String s1=sc.nextLine();
            String s2=sc.nextLine();
            System.out.println(delete(s1,s2));
        }
    }
    public static String delete(String s1,String s2){
        StringBuilder sb=new StringBuilder();
        char[] arr1=s1.toCharArray();
        for(int i=0;i<arr1.length;i++){
            if(help(arr1[i],s2)){
                continue;
            }else {
                sb.append(arr1[i]);
            }
        }
        return sb.toString();
    }
    public static boolean help(char c,String s2){
        char[] arr2=s2.toCharArray();
        for(int i=0;i<arr2.length;i++){
            if(arr2[i]==c){
                return true;
            }
        }
        return false;
    }
}
