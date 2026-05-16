public class Polymorphism {
    static class Animal {
        void speak() { System.out.println("..."); }
    }
    static class Cat extends Animal {
        @Override void speak() { System.out.println("야옹"); }
        void purr() { System.out.println("그르릉"); }
    }

    public static void main(String[] args) {
        Animal a = new Cat();
        a.speak();

        Cat c = (Cat) a;
        c.purr();
    }
}
