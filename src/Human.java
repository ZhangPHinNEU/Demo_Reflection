public class Human implements Person{

    public String name = null;
    public int age = 0;

   public Human(){
        this.name = null;
        this.age = 0;
    }

    public Human(String name, int age){
        this.name = name;
        this.age = age;
    }

    @Override
    public void sound() {
        System.out.println("say hahah!");
        System.out.println("my name is "+name+", and age is "+age);
    }
    public void soundPara(int l) {
        System.out.println("say hahah!");
        System.out.println("parameter is "+l);
    }

    public static void main(String[] args){
        Human human = new Human("张三", 25) ;
        human.sound();
    }
}
