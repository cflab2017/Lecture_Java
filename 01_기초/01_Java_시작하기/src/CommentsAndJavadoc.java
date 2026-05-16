/** 주석 종류를 보여 주는 데모. */
public class CommentsAndJavadoc {
    // 한 줄 주석
    /* 여러 줄
       주석 */
    public static void main(String[] args) {
        System.out.println(greeting("Java")); // 인라인 주석
    }

    /** 인사말을 반환. */
    static String greeting(String name) {
        return "Hello, " + name + "!";
    }
}
