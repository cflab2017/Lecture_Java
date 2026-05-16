public class FormatAndTextBlock {
    public static void main(String[] args) {
        String row = String.format("이름=%s, 점수=%d, 평균=%.2f", "지수", 95, 82.345);
        System.out.println(row);

        String json = """
            {
                "name": "지수",
                "score": 95
            }
            """;
        System.out.println(json);
    }
}
