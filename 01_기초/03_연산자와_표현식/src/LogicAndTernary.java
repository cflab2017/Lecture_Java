public class LogicAndTernary {
    public static void main(String[] args) {
        int score = 75;
        boolean passed = score >= 60;
        System.out.println("합격? " + passed);

        String grade = (score >= 90) ? "A"
                     : (score >= 80) ? "B"
                     : (score >= 70) ? "C"
                     : (score >= 60) ? "D"
                     : "F";
        System.out.println("등급: " + grade);

        boolean ok = score > 0 && score <= 100;
        System.out.println("유효 범위? " + ok);
    }
}
