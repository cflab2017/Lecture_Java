import java.util.List;

public class BoundedType {
    static <T extends Number> double average(List<T> xs) {
        if (xs.isEmpty()) return 0;
        double s = 0;
        for (T x : xs) s += x.doubleValue();
        return s / xs.size();
    }

    public static void main(String[] args) {
        System.out.println(average(List.of(10, 20, 30)));
        System.out.println(average(List.of(1.5, 2.5, 3.5)));
    }
}
