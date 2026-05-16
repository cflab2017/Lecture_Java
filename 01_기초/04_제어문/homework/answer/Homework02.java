/** switch expression 으로 메뉴 결정. */
public class Homework02 {
    public static void main(String[] args) {
        int menu = 2;
        String label = switch (menu) {
            case 1 -> "신메뉴";
            case 2 -> "추천";
            case 3, 4 -> "베스트셀러";
            default -> "없음";
        };
        System.out.println("선택: " + label);
    }
}
