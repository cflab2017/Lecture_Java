import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class SetBasics {
    public static void main(String[] args) {
        Set<Integer> h = new HashSet<>();
        Set<Integer> t = new TreeSet<>();
        for (int v : new int[]{3, 1, 4, 1, 5, 9, 2, 6}) {
            h.add(v);
            t.add(v);
        }
        System.out.println("HashSet : " + h);
        System.out.println("TreeSet : " + t);
    }
}
