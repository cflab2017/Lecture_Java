/** 평균: 오버로딩 + 가변 인자. */
public class Homework02 {
    public static void main(String[] args) {
        System.out.println("int avg(1,2,3,4) = " + avg(1, 2, 3, 4));
        System.out.println("double avg(1.5, 2.5) = " + avg(1.5, 2.5));
        System.out.println("empty = " + avg());
    }

    static double avg(int... nums) {
        if (nums.length == 0) return 0;
        int s = 0;
        for (int n : nums) s += n;
        return (double) s / nums.length;
    }

    static double avg(double... nums) {
        if (nums.length == 0) return 0;
        double s = 0;
        for (double n : nums) s += n;
        return s / nums.length;
    }
}
