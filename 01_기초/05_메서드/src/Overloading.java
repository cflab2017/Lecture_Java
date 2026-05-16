public class Overloading {
    public static void main(String[] args) {
        System.out.println(max(3, 7));
        System.out.println(max(3.5, 2.1));
        System.out.println(max("apple", "banana"));
    }

    static int max(int a, int b) { return a > b ? a : b; }
    static double max(double a, double b) { return a > b ? a : b; }
    static String max(String a, String b) { return a.compareTo(b) > 0 ? a : b; }
}
