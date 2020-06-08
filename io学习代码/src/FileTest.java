import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileTest {
    @Test
    public void t1(){
        //全路径
        File file=new File("C:\\Users\\黄庆99\\Desktop\\io学习代码\\data\\随便测.txt");
        System.out.println(file.exists());
        //相对路径，不建议在真实的项目中使用
        File file1=new File("data/随便测.txt");
        System.out.println(file1.exists());//文件是否存在
        //如何查看当前java代码的路径
        File file2=new File("");
        System.out.println(file2.getAbsolutePath());
        System.out.println(file1.exists());//文件是否存在
        System.out.println(file1.getPath());//获取路径
        System.out.println(file1.lastModified());//上次修改时间
    }

    @Test
    public void t2(){
        //返回目录下一级的子文件/子文件夹的数组
        File f3=new File("C:\\Users\\黄庆99\\Desktop\\io学习代码");
        File[] files=f3.listFiles();
        for(File child:files){
            System.out.println(child.getName());//文件名
        }
    }

    //递归获取目录下的所有文件夹
    public static List<File> list(File f){
        List<File> files=new ArrayList<>();
        if(f.isFile()){//是不是文件
            files.add(f);
        }else{
            File[] children=f.listFiles();
            for(File child:children){
                List<File> subs=list(child);
                files.addAll(subs);
            }
        }
        return files;
    }
    @Test
    public void t3(){
        File f4=new File("C:\\Users\\黄庆99\\Desktop\\io学习代码");
        List<File> children=list(f4);
        for(File child:children){
            System.out.println(child.getName());
        }
    }

}
