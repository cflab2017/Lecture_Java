public class PersonWithCtor {
    String name;
    int age;

    PersonWithCtor(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public static void main(String[] args) {
        PersonWithCtor a = new PersonWithCtor("지수", 21);
        PersonWithCtor b = new PersonWithCtor("민수", 25);
        System.out.println(a.name + ", " + a.age);
        System.out.println(b.name + ", " + b.age);
    }
}
