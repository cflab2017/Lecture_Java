/** 회문 판별. */
public class Homework01 {
    public static void main(String[] args) {
        String[] cases = { "level", "Hello", "A man a plan a canal Panama" };
        for (String s : cases) {
            System.out.println(s + " -> " + isPalindrome(s));
        }
    }

    static boolean isPalindrome(String s) {
        String t = s.replace(" ", "").toLowerCase();
        return t.equals(new StringBuilder(t).reverse().toString());
    }
}
