package frank;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

//深拷贝,序列化和反序列化
//io的学习
//Serializable序列化接口
public class SerializableTest implements Serializable {
    private String name;
     List<Food> foods =new ArrayList<>();

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        SerializableTest t=new SerializableTest();
        t.name="快餐店";
        t.foods.add(new Food("小鸟伏特加"));
        t.foods.add(new Food("猫登伏特加"));
        t.foods.add(new Food("老八秘制小汉堡"));
        //输出
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        ObjectOutputStream oos=new ObjectOutputStream(baos);
        oos.writeObject(t);//序列化Java对象为二进制数据
        //改变内容
        t.name="慢慢吃";
        t.foods.get(1).name="小香肠";
        //读入
        ByteArrayInputStream bais=new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois=new ObjectInputStream(bais);
        SerializableTest t2=(SerializableTest) ois.readObject();
        System.out.println(t);
        System.out.println(t2);
    }

    @Override
    public String toString() {
        return "SerializableTest{" +
                "name='" + name + '\'' +
                ", foods=" + foods +
                '}';
    }

    private static class Food implements Serializable{
        private String name;
        public Food(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Food{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
}
