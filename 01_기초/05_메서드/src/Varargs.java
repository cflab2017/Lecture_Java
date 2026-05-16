public class Varargs {
    public static void main(String[] args) {
        System.out.println(sumAll());
        System.out.println(sumAll(1, 2, 3));
        System.out.println(sumAll(10, 20, 30, 40));
    }

    /** 가변 개수의 정수를 모두 합산. */
    static int sumAll(int... nums) {
        int s = 0;
        for (int n : nums) s += n;
        return s;
    }
}
