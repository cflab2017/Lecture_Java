import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;

public class DurationAndPeriod {
    public static void main(String[] args) {
        LocalTime a = LocalTime.of(9, 0);
        LocalTime b = LocalTime.of(17, 30);
        Duration work = Duration.between(a, b);
        System.out.println("근무 분: " + work.toMinutes());

        LocalDate birth = LocalDate.of(2005, 3, 1);
        LocalDate now = LocalDate.of(2026, 5, 16);
        Period age = Period.between(birth, now);
        System.out.printf("나이: %d년 %d개월 %d일%n",
            age.getYears(), age.getMonths(), age.getDays());
    }
}
