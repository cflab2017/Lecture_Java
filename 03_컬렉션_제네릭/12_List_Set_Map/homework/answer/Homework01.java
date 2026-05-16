import java.util.Set;
import java.util.TreeSet;

/** 중복 제거 + 정렬. */
public class Homework01 {
    public static void main(String[] args) {
        int[] arr = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5};
        Set<Integer> s = new TreeSet<>();
        for (int n : arr) s.add(n);
        System.out.println(s);
    }
}
