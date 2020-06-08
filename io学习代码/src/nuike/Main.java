package nuike;

import java.io.StringWriter;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String A=sc.next();
        String B=sc.next();
        int count=0;
        for(int i=0;i<A.length();i++){
            StringBuilder sb=new StringBuilder(A);
            sb.insert(i,B);
            if(help(sb.toString())){
                count++;
            }
        }
        System.out.println(count);

    }
    public static boolean help(String s){
        Stack<Character> stack=new Stack<>();
        for(int i=0;i<s.length();i++){
            stack.push(s.charAt(i));
        }
        int i=0;
        while (!stack.isEmpty()){
            if(!stack.pop().equals(s.charAt(i))){
                return false;
            }
            i++;
        }
        return true;
    }

}
