import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;



public class ReflectDemo {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        getClassObject_1();
        getClassObject_2();
        getClassObject_3();
        getClassObject_4();
        getClassObject_5();
        getClassObject_6();

    }


    //获取字节码对象的方法1，Object类中的getClass()方法
    //根据类的实例通过getClass()方法得到实例所属类型
    private static void getClassObject_1() {
        Human Tom = new Human("Tom", 25);
        Human Jerry = new Human("Jerry",19);
        Class class_Tom = Tom.getClass();
        Class class_Jerry = Jerry.getClass();
        System.out.println(class_Tom);
        System.out.println(class_Tom.equals(class_Jerry));
    }

    //获取字节码对象的方法2，任何类都具备一个静态的属性.class来获取对应的class对象
    //根据类通过.class得到类的所属类型
    private static void getClassObject_2() {
        Class c1 = Human.class;
        Class c2 = Human.class;
        System.out.println(c1);
        System.out.println(c1.equals(c2));
    }

    //获取字节码对象的方法3，任何类都具备一个静态的属性.class来获取对应的class对象
    //方法相对简单，但还是要明确类中的静态成员，还不够扩展
    private static void getClassObject_3() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        String className = "Human";//一定要使用完整地址，com.dd.aa.Human
        //找寻类名对应的字节码文件，并加载进内存
        Class c1 = Class.forName(className);
        System.out.println(c1);
        //产生新实例
        Object obj = c1.newInstance();//空参初始化,带参数不能初始化
        System.out.println(obj);
    }

    //获取指定class中的构造器
    private static void getClassObject_4() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchFieldException {
        String string = "Human";
        Class c1 = Class.forName(string);

        //无参构造器
        Constructor constructor1 = c1.getConstructor();
        Object obj1 = constructor1.newInstance();
        System.out.println(obj1);
        //有参构造器
        Constructor constructor2 = c1.getConstructor(String.class, int.class);
        Object obj2 = constructor2.newInstance("lilei",29);
        System.out.println(obj2);
    }
    //获取指定class中的字段
    private static void getClassObject_5() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        String className = "Human";//一定要使用完整地址，com.dd.aa.Human
        Class c1 = Class.forName(className);
        System.out.println(c1);
        Object obj = c1.newInstance();//空参初始化,带参数不能初始化
        //获取字节码文件对应类的字段
        Field age = c1.getField("age");//公共属性用getFiled(),公共加私有getDeclaredFiled();
        //file.setAccessible(true);//对私有字段 也可以访问，暴力访问
        System.out.println(age);
        age.set(obj,28);
        Object o = age.get(obj);
        System.out.println(o);

    }

    //获取指定class中的公共函数，公共方法
    private static void getClassObject_6() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchFieldException {
        String string = "Human";
        Class c1 = Class.forName(string);
        //获取全部方法
        Method [] methods = c1.getDeclaredMethods();//获取本类汇总，所有方法；只获取公有方法getMethods()
        for (Method method: methods) {System.out.println(method); }
        //获取无参方法
        Method method = c1.getMethod("sound",null);
        System.out.println(method);
        method.invoke(c1.newInstance(),null);
        //获取有参方法
        Method method1 = c1.getMethod("soundPara", int.class);
        method1.invoke(c1.newInstance(),29);

    }
}
