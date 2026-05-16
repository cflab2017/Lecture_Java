/** 소수 판별. */
public class Homework01 {
    public static void main(String[] args) {
        for (int n = 2; n <= 10; n++) {
            System.out.println(n + " -> " + isPrime(n));
        }
    }

    static boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}
