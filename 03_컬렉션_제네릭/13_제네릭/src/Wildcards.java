import java.util.ArrayList;
import java.util.List;

public class Wildcards {
    /** 어떤 숫자 리스트든 합산. */
    static double sum(List<? extends Number> xs) {
        double s = 0;
        for (Number n : xs) s += n.doubleValue();
        return s;
    }

    /** Integer 또는 그 부모 자리에 1~3 추가. */
    static void addNums(List<? super Integer> xs) {
        xs.add(1); xs.add(2); xs.add(3);
    }

    public static void main(String[] args) {
        System.out.println(sum(List.of(1, 2, 3)));
        System.out.println(sum(List.of(1.5, 2.5)));

        List<Number> ns = new ArrayList<>();
        addNums(ns);
        System.out.println(ns);
    }
}
