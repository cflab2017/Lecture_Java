public class Conversion {
    public static void main(String[] args) {
        int i = 100;
        double d = i;
        System.out.println("int -> double: " + d);

        double pi = 3.99;
        int truncated = (int) pi;
        System.out.println("double -> int (절삭): " + truncated);

        int big = 1_000;
        byte overflow = (byte) big;
        System.out.println("byte 오버플로: " + overflow);
    }
}
