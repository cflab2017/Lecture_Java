import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/** 출근 시각 두 가지 포맷. */
public class Homework02 {
    public static void main(String[] args) {
        LocalDateTime dt = LocalDateTime.of(2026, 5, 16, 9, 0);
        DateTimeFormatter a = DateTimeFormatter.ofPattern("yyyy년 M월 d일 a h시 m분", Locale.KOREAN);
        DateTimeFormatter b = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        System.out.println(dt.format(a));
        System.out.println(dt.format(b));
    }
}
