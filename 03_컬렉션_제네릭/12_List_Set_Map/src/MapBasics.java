import java.util.HashMap;
import java.util.Map;

public class MapBasics {
    public static void main(String[] args) {
        Map<String, Integer> score = new HashMap<>();
        score.put("국어", 90);
        score.put("수학", 85);
        score.put("영어", 78);

        for (Map.Entry<String, Integer> e : score.entrySet()) {
            System.out.println(e.getKey() + " -> " + e.getValue());
        }
        System.out.println("국어=" + score.get("국어"));
        System.out.println("프랑스어=" + score.getOrDefault("프랑스어", -1));
    }
}
