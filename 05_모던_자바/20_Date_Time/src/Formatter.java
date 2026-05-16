import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Formatter {
    public static void main(String[] args) {
        LocalDateTime dt = LocalDateTime.of(2026, 5, 16, 9, 30, 45);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
        System.out.println(dt.format(fmt));

        LocalDateTime parsed = LocalDateTime.parse("2026-05-16T09:30:45");
        System.out.println(parsed);
    }
}
