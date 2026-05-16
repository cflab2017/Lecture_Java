import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/** 메서드 참조로 정렬. */
public class Homework02 {
    record Book(String title, int pages) {}

    public static void main(String[] args) {
        List<Book> books = new ArrayList<>(List.of(
            new Book("B", 300),
            new Book("A", 100),
            new Book("C", 200)
        ));

        books.sort(Comparator.comparingInt(Book::pages));
        System.out.println("페이지 오름차순: " + books);

        books.sort(Comparator.comparing(Book::title));
        System.out.println("제목 알파벳순: " + books);
    }
}
