/** Money record. */
public class Homework02 {
    record Money(long amount, String currency) {}

    public static void main(String[] args) {
        Money m1 = new Money(1000, "KRW");
        Money m2 = new Money(1000, "KRW");
        Money m3 = new Money(1000, "USD");
        System.out.println("m1=" + m1);
        System.out.println("m1.equals(m2)=" + m1.equals(m2));
        System.out.println("m1.equals(m3)=" + m1.equals(m3));
    }
}
