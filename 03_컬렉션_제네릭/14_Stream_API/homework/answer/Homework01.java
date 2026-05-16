import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/** 학생 점수 분석. */
public class Homework01 {
    record Student(String name, String dept, int score) {}

    public static void main(String[] args) {
        List<Student> ss = List.of(
            new Student("Alice", "CS", 85),
            new Student("Bob",   "CS", 70),
            new Student("Cathy", "MATH", 92),
            new Student("Dan",   "MATH", 65),
            new Student("Eve",   "PHY", 80)
        );

        double avg = ss.stream().mapToInt(Student::score).average().orElse(0);
        Student best = ss.stream().max(Comparator.comparingInt(Student::score)).orElseThrow();
        Map<String, Double> byDept = ss.stream()
            .collect(Collectors.groupingBy(
                Student::dept,
                TreeMap::new,
                Collectors.averagingInt(Student::score)));

        System.out.printf("전체 평균: %.1f%n", avg);
        System.out.println("최고점: " + best.name() + " (" + best.score() + ")");
        System.out.println("학과별 평균");
        byDept.forEach((d, a) -> System.out.printf("%s -> %.1f%n", d, a));
    }
}
