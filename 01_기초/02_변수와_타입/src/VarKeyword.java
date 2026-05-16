import java.util.ArrayList;
import java.util.List;

public class VarKeyword {
    public static void main(String[] args) {
        var name = "지수";
        var list = new ArrayList<String>();
        list.add("Java");
        list.add("Spring");

        for (var s : list) {
            System.out.println(s);
        }

        List<Integer> nums = List.of(1, 2, 3);
        System.out.println("이름: " + name);
        System.out.println("타입은: " + nums.getClass().getSimpleName());
    }
}
