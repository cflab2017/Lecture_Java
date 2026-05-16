public class EqualsVsEqEq {
    public static void main(String[] args) {
        String a = "Java";
        String b = "Java";
        String c = new String("Java");
        System.out.println("a == b      : " + (a == b));
        System.out.println("a == c      : " + (a == c));
        System.out.println("a.equals(c) : " + a.equals(c));

        Integer x = 100;
        Integer y = 100;
        Integer z = 200;
        Integer w = 200;
        System.out.println("100==100? " + (x == y));
        System.out.println("200==200? " + (z == w));
        System.out.println("equals    : " + z.equals(w));
    }
}
