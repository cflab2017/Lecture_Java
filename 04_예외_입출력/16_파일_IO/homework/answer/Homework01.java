import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/** TODO 파일 작성·읽기·삭제. */
public class Homework01 {
    public static void main(String[] args) throws IOException {
        Path p = Path.of("todo.txt");
        Files.write(p, List.of("우유 사기", "숙제 끝내기", "운동 30분"));
        List<String> lines = Files.readAllLines(p);
        for (int i = 0; i < lines.size(); i++) {
            System.out.println((i + 1) + ") " + lines.get(i));
        }
        Files.deleteIfExists(p);
        System.out.println("삭제 완료");
    }
}
