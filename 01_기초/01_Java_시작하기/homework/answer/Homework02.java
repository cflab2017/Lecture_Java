/** 명령줄 인자에 따른 인사말 출력. */
public class Homework02 {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("사용법: java Homework02 <이름>");
            return;
        }
        System.out.println("안녕하세요, " + args[0] + "님!");
    }
}
