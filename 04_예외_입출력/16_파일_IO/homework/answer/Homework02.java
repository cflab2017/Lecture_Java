import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/** 파일을 읽어 단어 수 세기. */
public class Homework02 {
    public static void main(String[] args) throws IOException {
        Path p = Path.of("essay.txt");
        Files.writeString(p, "Java is fun. Java is powerful.");
        String text = Files.readString(p).trim();
        String[] words = text.split("[\\s.]+");
        int count = 0;
        for (String w : words) if (!w.isEmpty()) count++;
        System.out.println("단어 수=" + count);
        Files.deleteIfExists(p);
    }
}
