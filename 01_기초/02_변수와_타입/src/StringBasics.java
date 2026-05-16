public class StringBasics {
    public static void main(String[] args) {
        String a = "hello";
        String b = "hello";
        String c = new String("hello");
        System.out.println("a == b : " + (a == b));
        System.out.println("a == c : " + (a == c));
        System.out.println("a.equals(c) : " + a.equals(c));
        System.out.println("length : " + a.length());
        System.out.println("upper  : " + a.toUpperCase());
    }
}
