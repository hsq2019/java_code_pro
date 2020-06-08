package nuike;

public class Finder {
    public static void main(String[] args) {
        int[] arr= new int[]{1,3,5,2,2};
        int num=findKth(arr,5,3);
        System.out.println(num);
    }
    public static int findKth(int[] a,int n,int k){
        help(a,0,n-1);
//        int count=0;
//        int num=0;
//        for(int i=n-1;i<=0;i--){
//            while (a[i]==a[i-1]){
//                i--;
//            }
//            count++;
//            if(count==k){
//                num=a[i];
//                break;
//            }
//        }
//        return num;

        return a[k-1];
    }
    public static int[] help(int[] arr,int start,int end){
        if(start<end){
            int i=start;
            int j=end;
            int x=arr[i];//以最左边的数是基准值
            while (i<j){
                while (i<j&&arr[j]>=x){
                    j--;
                }
                if(i<j){
                    arr[i]=arr[j];
                    i++;
                }
                while (i<j&&arr[i]<=x){
                    i++;
                }
                if(i<j){
                    arr[j]=arr[i];
                    j--;
                }
            }
            arr[i]=x;
            help(arr,start,i-1);
            help(arr,i+1,end);
        }
        return arr;
    }
}
