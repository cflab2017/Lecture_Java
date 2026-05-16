/** 프로필 변수 출력. */
public class Homework01 {
    public static void main(String[] args) {
        String name = "홍길동";
        int age = 21;
        double height = 175.30;
        boolean student = true;
        char grade = 'A';
        System.out.printf("이름=%s, 나이=%d, 키=%.2f, 학생=%b, 등급=%c%n",
                name, age, height, student, grade);
    }
}
