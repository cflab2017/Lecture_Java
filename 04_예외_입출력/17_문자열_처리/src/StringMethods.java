public class StringMethods {
    public static void main(String[] args) {
        String s = " Hello, Java World ";
        System.out.println("length=" + s.length());
        System.out.println("trim=" + s.trim() + "|");
        System.out.println("upper=" + s.toUpperCase());
        System.out.println("startsWith Hello? " + s.trim().startsWith("Hello"));
        System.out.println("sub=" + s.trim().substring(7, 11));
        System.out.println("rep=" + s.trim().replace("Java", "Spring"));
        for (String w : s.trim().split(",\\s*")) {
            System.out.println("token=" + w);
        }
    }
}
