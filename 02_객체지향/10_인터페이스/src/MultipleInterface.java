public class MultipleInterface {
    interface Flyable { void fly(); }
    interface Swimmable { void swim(); }

    static class Duck implements Flyable, Swimmable {
        @Override public void fly() { System.out.println("오리가 난다"); }
        @Override public void swim() { System.out.println("오리가 헤엄친다"); }
    }

    public static void main(String[] args) {
        Duck d = new Duck();
        d.fly();
        d.swim();

        Flyable f = d;
        Swimmable s = d;
        f.fly();
        s.swim();
    }
}
