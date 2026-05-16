import java.time.LocalDate;

public class DateBasics {
    public static void main(String[] args) {
        LocalDate today = LocalDate.of(2026, 5, 16);
        LocalDate xmas = LocalDate.of(2026, 12, 25);
        System.out.println("오늘: " + today);
        System.out.println("크리스마스: " + xmas);
        System.out.println("내일: " + today.plusDays(1));
        System.out.println("한 달 전: " + today.minusMonths(1));
        System.out.println("크리스마스 전? " + today.isBefore(xmas));
    }
}
