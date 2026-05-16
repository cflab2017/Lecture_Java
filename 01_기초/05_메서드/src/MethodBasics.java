public class MethodBasics {
    public static void main(String[] args) {
        int s = add(3, 4);
        System.out.println("3 + 4 = " + s);
        greet("지수");
    }

    /** 두 정수의 합. */
    static int add(int a, int b) {
        return a + b;
    }

    /** 인사 출력. */
    static void greet(String name) {
        System.out.println("Hello, " + name + "!");
    }
}
