import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class AppendAndDelete {
    public static void main(String[] args) throws IOException {
        Path p = Path.of("notes.txt");
        Files.writeString(p, "첫 줄\n");
        Files.writeString(p, "두 번째 줄\n", StandardOpenOption.APPEND);
        System.out.println(Files.readString(p));
        Files.deleteIfExists(p);
        System.out.println("삭제 완료");
    }
}
