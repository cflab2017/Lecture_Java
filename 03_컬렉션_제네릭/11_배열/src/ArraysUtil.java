import java.util.Arrays;

public class ArraysUtil {
    public static void main(String[] args) {
        int[] a = {3, 1, 4, 1, 5, 9, 2, 6};
        Arrays.sort(a);
        System.out.println(Arrays.toString(a));

        int[] head = Arrays.copyOf(a, 4);
        System.out.println(Arrays.toString(head));

        int[] slice = Arrays.copyOfRange(a, 2, 6);
        System.out.println(Arrays.toString(slice));
    }
}
