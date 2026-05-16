public class Primitives {
    public static void main(String[] args) {
        byte b = 10;
        short s = 200;
        int i = 100_000;
        long l = 9_999_999_999L;
        float f = 3.14f;
        double d = 3.141592653589793;
        boolean t = true;
        char c = 'A';
        System.out.printf("byte=%d, short=%d, int=%d, long=%d%n", b, s, i, l);
        System.out.printf("float=%.2f, double=%.6f%n", f, d);
        System.out.printf("boolean=%b, char=%c(%d)%n", t, c, (int) c);
    }
}
