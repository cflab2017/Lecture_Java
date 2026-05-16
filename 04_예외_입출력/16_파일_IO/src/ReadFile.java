import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ReadFile {
    public static void main(String[] args) throws IOException {
        Path p = Path.of("greeting.txt");
        Files.writeString(p, "Hello\n안녕\n");

        List<String> lines = Files.readAllLines(p);
        for (int i = 0; i < lines.size(); i++) {
            System.out.println((i + 1) + ": " + lines.get(i));
        }
    }
}
