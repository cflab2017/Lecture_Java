/** 2~5 단 구구단. */
public class Homework03 {
    public static void main(String[] args) {
        for (int dan = 2; dan <= 5; dan++) {
            for (int i = 1; i <= 9; i++) {
                System.out.println(dan + " * " + i + " = " + (dan * i));
            }
            System.out.println();
        }
    }
}
