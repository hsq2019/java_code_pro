package frank;

import org.junit.Test;

//io流分类的练习
import java.io.*;

public class FileIOTest {
    @Test
    //字节流
    public void t1() throws IOException {
        FileInputStream fis=null;
        //文件的字节流输入
        //使用FileInputStream+绝对路径
        //使用ClassLoader+相对路径(推荐)
        try {
            fis= (FileInputStream) this.getClass().getClassLoader().getResourceAsStream("随便.txt");
             //fis=new FileInputStream("C:\\Users\\黄庆99\\Desktop\\io学习代码\\data\\随便测.txt");
            //读取操作，从当前位置编译多少位（read,new String中的参数）
            byte[] bytes=new byte[1024];
            int len;
            while((len=fis.read(bytes))!=-1){
                String s=new String(bytes,0,len);
                System.out.println(s);
            }
        } finally {
            if(fis!=null){
                fis.close();
            }
        }
    }

    //字符流读入
    @Test
    public void t2() throws IOException {
        FileReader reader=new FileReader("C:\\Users\\黄庆99\\Desktop\\io学习代码\\data\\随便测.txt");
        char[] chars=new char[1024];
        int len;
        while ((len=reader.read(chars))!=-1){
            String s=new String(chars,0,len);
            System.out.println(s);
        }
    }

    //转换流
    @Test
    public void t3() throws IOException {
        //字节流转换成字符流
        //字节输入流
        FileInputStream fis=new FileInputStream("C:\\Users\\黄庆99\\Desktop\\io学习代码\\data\\随便测.txt");
        InputStreamReader isr=new InputStreamReader(fis);//输入转换
        BufferedReader br=new BufferedReader(isr);//缓冲字符流
        String line;//按行
        while ((line=br.readLine())!=null){
            System.out.println(line);
        }
    }

    @Test
    public void t4() throws IOException {
        FileInputStream fis=new FileInputStream("C:\\Users\\黄庆99\\Desktop\\io学习代码\\data\\随便测.txt");
        BufferedInputStream bis=new BufferedInputStream(fis);
        byte[] bytes=new byte[1024];
        int len;
        while ((len=bis.read(bytes))!=-1){
            String s=new String(bytes,0,len);
            System.out.println(s);
        }
    }

    @Test
    //输出流
    public void t5() throws IOException {
        //覆盖原文的方式
        FileOutputStream fos=new FileOutputStream("C:\\Users\\黄庆99\\Desktop\\io学习代码\\data\\随便测.txt");
        //添加到文件末尾
        //FileOutputStream fos=new FileOutputStream("C:\\Users\\黄庆99\\Desktop\\io学习代码\\data\\随便测.txt",true);

        //不会自动刷新，需要手动刷新缓冲区
        PrintWriter pw=new PrintWriter(fos);
        pw.println("追加1");
        pw.println("追加2");
        pw.println("追加3");//如果没有手动刷新的时候，文件就被覆盖成空的，内容看不出来
        pw.flush();//手动刷新，手动刷新之后，才能显示追击内容

        //PrintWriter pw=new PrintWriter(fos,true);//会自动刷新缓冲区

    }

    @Test
    //输出流
    public void t6() throws IOException {
        //覆盖原文的方式
        FileOutputStream fos=new FileOutputStream("C:\\Users\\黄庆99\\Desktop\\io学习代码\\data\\随便测.txt");
        //添加到文件末尾
        //FileOutputStream fos=new FileOutputStream("C:\\Users\\黄庆99\\Desktop\\io学习代码\\data\\随便测.txt",true);

        //不会自动刷新，需要手动刷新缓冲区
        OutputStreamWriter osw=new OutputStreamWriter(fos);//输出转换
        BufferedWriter bw=new BufferedWriter(osw);
        bw.write("追加1");
        bw.newLine();//手动换行
        bw.write("追加2");
        bw.newLine();//手动换行
        bw.write("追加3");
        bw.newLine();//手动换行
        bw.flush();//手动刷新
    }

}
