import java.util.List;
import java.util.Map;
import java.util.Set;

public class ImmutableCollections {
    public static void main(String[] args) {
        List<String> l = List.of("A", "B", "C");
        Set<Integer> s = Set.of(1, 2, 3);
        Map<String, Integer> m = Map.of("a", 1, "b", 2);

        System.out.println(l);
        System.out.println(s);
        System.out.println(m);

        try {
            l.add("D");
        } catch (UnsupportedOperationException e) {
            System.out.println("불변: 수정 불가");
        }
    }
}
