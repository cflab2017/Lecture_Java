public class SwitchExpression {
    public static void main(String[] args) {
        int day = 3;
        String kind = switch (day) {
            case 1, 2, 3, 4, 5 -> "평일";
            case 6, 7 -> "주말";
            default -> "오류";
        };
        System.out.println(day + "요일: " + kind);

        int n = 2;
        String word = switch (n) {
            case 1 -> "one";
            case 2 -> {
                System.out.println("two!");
                yield "two";
            }
            default -> "?";
        };
        System.out.println(word);
    }
}
