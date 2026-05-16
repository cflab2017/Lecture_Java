import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WriteFile {
    public static void main(String[] args) throws IOException {
        Path p = Path.of("greeting.txt");
        Files.writeString(p, "Hello\n안녕\n");
        System.out.println(p.toAbsolutePath() + " 쓰기 완료");
    }
}
