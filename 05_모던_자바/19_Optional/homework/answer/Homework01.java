import java.util.Map;
import java.util.Optional;

/** 사용자 이메일 조회 with Optional. */
public class Homework01 {
    static Map<String, String> emails = Map.of("id1", "a@x.com", "id2", "b@y.com");

    public static void main(String[] args) {
        for (String id : new String[]{"id1", "id3"}) {
            String email = findEmail(id).orElse("없음");
            System.out.println(id + " -> " + email);
        }
    }

    static Optional<String> findEmail(String userId) {
        return Optional.ofNullable(emails.get(userId));
    }
}
