package nuike;

import java.util.Scanner;

//换空瓶子喝水
//3个空瓶子可以换1个汽水
//在自己有2个空瓶的时候，可以向老板借
//如果换的饮料的数量小于等于0，就不输出
public class drink {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while (scanner.hasNextInt()){
            int empty=scanner.nextInt();
            if(help(empty)>0){
                System.out.println(help(empty));
            }
        }
    }
    public static int help(int empty){
        int num=(int)(empty/2);
        return num;
    }
}
