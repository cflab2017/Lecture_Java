public class Immutability {
    public static void main(String[] args) {
        String s = "X";
        long start = System.nanoTime();
        for (int i = 0; i < 1000; i++) s += "Y";
        long t1 = System.nanoTime() - start;
        System.out.println("String += 길이=" + s.length() + ", 시간=" + t1 + "ns");

        StringBuilder sb = new StringBuilder("X");
        long start2 = System.nanoTime();
        for (int i = 0; i < 1000; i++) sb.append("Y");
        long t2 = System.nanoTime() - start2;
        System.out.println("StringBuilder 길이=" + sb.length() + ", 시간=" + t2 + "ns");
    }
}
