public class FunctionalDemo {
    @FunctionalInterface
    interface Calc {
        int apply(int a, int b);
    }

    public static void main(String[] args) {
        Calc add = (a, b) -> a + b;
        Calc mul = (a, b) -> a * b;
        Calc max = (a, b) -> a > b ? a : b;

        System.out.println("add(2,3)=" + add.apply(2, 3));
        System.out.println("mul(2,3)=" + mul.apply(2, 3));
        System.out.println("max(2,3)=" + max.apply(2, 3));
    }
}
