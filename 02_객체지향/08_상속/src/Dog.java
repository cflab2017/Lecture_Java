public class Dog extends Animal {
    public Dog(String name) { super(name); }

    @Override
    public void speak() {
        System.out.println(name + ": 멍멍!");
    }

    public static void main(String[] args) {
        Animal a = new Animal("동물");
        Dog d = new Dog("바둑이");
        a.speak();
        d.speak();
    }
}
