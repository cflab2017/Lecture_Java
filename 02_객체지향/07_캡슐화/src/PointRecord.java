public class PointRecord {
    public record Point(int x, int y) {}

    public static void main(String[] args) {
        Point a = new Point(1, 2);
        Point b = new Point(1, 2);
        System.out.println(a);
        System.out.println("a.x = " + a.x());
        System.out.println("a.equals(b) = " + a.equals(b));
    }
}
