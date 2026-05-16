public class Person {
    String name;
    int age;

    void hello() {
        System.out.println("Hi, I'm " + name + " (" + age + ")");
    }

    public static void main(String[] args) {
        Person p = new Person();
        p.name = "지수";
        p.age = 21;
        p.hello();
    }
}
