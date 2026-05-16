import java.util.List;
import java.util.Optional;

public class Reduce {
    public static void main(String[] args) {
        List<Integer> xs = List.of(3, 1, 4, 1, 5, 9, 2, 6);
        int sum = xs.stream().reduce(0, Integer::sum);
        Optional<Integer> max = xs.stream().reduce(Integer::max);
        System.out.println("sum=" + sum);
        System.out.println("max=" + max.orElseThrow());
    }
}
