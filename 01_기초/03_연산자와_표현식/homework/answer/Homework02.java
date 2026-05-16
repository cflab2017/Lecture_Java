/** 삼항 연산자로 학점 결정. */
public class Homework02 {
    public static void main(String[] args) {
        int score = 73;
        String grade = (score >= 90) ? "A"
                     : (score >= 80) ? "B"
                     : (score >= 70) ? "C"
                     : (score >= 60) ? "D"
                     : "F";
        System.out.println("점수=" + score + " -> 등급=" + grade);
    }
}
