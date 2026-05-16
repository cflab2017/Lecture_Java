import java.util.List;

/** instanceof 패턴 매칭. */
public class Homework02 {
    public static void main(String[] args) {
        Object[] objs = { 42, "Hello", List.of(1, 2, 3) };
        for (Object o : objs) {
            describe(o);
        }
    }

    static void describe(Object o) {
        if (o instanceof Integer n) {
            System.out.println("숫자: " + n);
        } else if (o instanceof String s) {
            System.out.println("문자열: " + s.length() + "자");
        } else {
            System.out.println("알 수 없음");
        }
    }
}
