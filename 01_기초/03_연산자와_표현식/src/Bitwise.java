public class Bitwise {
    public static void main(String[] args) {
        int a = 0b1010;
        int b = 0b1100;
        System.out.printf("a & b = %d (%s)%n", a & b, Integer.toBinaryString(a & b));
        System.out.printf("a | b = %d (%s)%n", a | b, Integer.toBinaryString(a | b));
        System.out.printf("a ^ b = %d (%s)%n", a ^ b, Integer.toBinaryString(a ^ b));
        System.out.printf("~a    = %d%n", ~a);
        System.out.printf("a<<1  = %d%n", a << 1);
        System.out.printf("a>>1  = %d%n", a >> 1);
    }
}
