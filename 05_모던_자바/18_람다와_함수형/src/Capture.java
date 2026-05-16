public class Capture {
    public static void main(String[] args) {
        int x = 10;
        Runnable r = () -> System.out.println("x=" + x);
        r.run();

        for (int i = 0; i < 3; i++) {
            final int idx = i;
            Runnable rr = () -> System.out.println("idx=" + idx);
            rr.run();
        }
    }
}
