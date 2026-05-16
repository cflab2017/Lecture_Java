/** 비트 AND 로 짝/홀 판별. */
public class Homework03 {
    public static void main(String[] args) {
        for (int n = 1; n <= 5; n++) {
            String kind = ((n & 1) == 0) ? "짝수" : "홀수";
            System.out.println(n + " -> " + kind);
        }
    }
}
