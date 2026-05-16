import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class GroupingBy {
    record Student(String name, String dept, int score) {}

    public static void main(String[] args) {
        List<Student> ss = List.of(
            new Student("Alice", "CS", 85),
            new Student("Bob",   "CS", 70),
            new Student("Cathy", "MATH", 92),
            new Student("Dan",   "MATH", 65),
            new Student("Eve",   "PHY", 80)
        );

        Map<String, Double> avgByDept = ss.stream()
            .collect(Collectors.groupingBy(
                Student::dept,
                TreeMap::new,
                Collectors.averagingInt(Student::score)));

        avgByDept.forEach((d, a) -> System.out.printf("%s -> %.1f%n", d, a));
    }
}
