import java.util.Arrays;
import java.util.List;

public class IntVsInteger {
    public static void main(String[] args) {
        int[] xs = new int[3];
        Integer[] ys = new Integer[3];
        System.out.println("xs[0]=" + xs[0]);
        System.out.println("ys[0]=" + ys[0]);

        Integer[] zs = {1, 2, 3};
        List<Integer> list = Arrays.asList(zs);
        System.out.println(list);
    }
}
