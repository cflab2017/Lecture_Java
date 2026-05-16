import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class BufferedReadDemo {
    public static void main(String[] args) throws IOException {
        Path p = Path.of("greeting.txt");
        Files.writeString(p, "Hello\n안녕\n");

        try (BufferedReader br = Files.newBufferedReader(p)) {
            String line;
            int n = 0;
            while ((line = br.readLine()) != null) {
                n++;
                System.out.printf("L%d: %s%n", n, line);
            }
        }
    }
}
