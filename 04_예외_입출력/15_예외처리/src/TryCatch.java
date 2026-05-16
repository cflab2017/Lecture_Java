public class TryCatch {
    public static void main(String[] args) {
        try {
            int n = Integer.parseInt("abc");
            System.out.println(n);
        } catch (NumberFormatException e) {
            System.out.println("숫자 아님: " + e.getMessage());
        } finally {
            System.out.println("종료 정리");
        }
        System.out.println("프로그램 계속");
    }
}
