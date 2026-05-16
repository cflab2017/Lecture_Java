public class ThrowsAndCustom {
    static class InvalidAgeException extends RuntimeException {
        public InvalidAgeException(String msg) { super(msg); }
    }

    static void register(String name, int age) {
        if (age < 0) {
            throw new InvalidAgeException("나이는 0 이상: " + age);
        }
        System.out.println("등록: " + name + " (" + age + ")");
    }

    public static void main(String[] args) {
        try {
            register("지수", 21);
            register("미상", -1);
        } catch (InvalidAgeException e) {
            System.out.println("거부: " + e.getMessage());
        }
    }
}
