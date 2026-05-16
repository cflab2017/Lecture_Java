import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/** D-Day 계산. */
public class Homework01 {
    public static void main(String[] args) {
        LocalDate base = LocalDate.of(2026, 5, 16);
        LocalDate target = LocalDate.of(2026, 12, 25);
        long days = ChronoUnit.DAYS.between(base, target);
        System.out.println("D-" + days + "일");
    }
}
