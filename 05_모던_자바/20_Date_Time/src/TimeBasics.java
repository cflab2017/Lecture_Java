import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TimeBasics {
    public static void main(String[] args) {
        LocalDateTime ldt = LocalDateTime.of(2026, 5, 16, 9, 30);
        System.out.println("로컬: " + ldt);

        ZonedDateTime seoul = ldt.atZone(ZoneId.of("Asia/Seoul"));
        ZonedDateTime ny = seoul.withZoneSameInstant(ZoneId.of("America/New_York"));
        System.out.println("서울: " + seoul);
        System.out.println("뉴욕: " + ny);
    }
}
