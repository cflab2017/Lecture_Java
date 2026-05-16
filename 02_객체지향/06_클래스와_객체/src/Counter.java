public class Counter {
    int count;

    void increment() {
        count++;
    }

    public static void main(String[] args) {
        Counter c1 = new Counter();
        Counter c2 = new Counter();
        c1.increment();
        c1.increment();
        c2.increment();
        System.out.println("c1=" + c1.count + ", c2=" + c2.count);
    }
}
