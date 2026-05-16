public class ExceptionChaining {
    static void load() {
        try {
            Integer.parseInt("nope");
        } catch (NumberFormatException low) {
            throw new IllegalStateException("설정 로드 실패", low);
        }
    }

    public static void main(String[] args) {
        try {
            load();
        } catch (IllegalStateException e) {
            System.out.println("바깥: " + e.getMessage());
            System.out.println("원인: " + e.getCause());
        }
    }
}
