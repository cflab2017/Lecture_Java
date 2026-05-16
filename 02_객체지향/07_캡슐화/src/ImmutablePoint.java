public final class ImmutablePoint {
    private final int x;
    private final int y;

    public ImmutablePoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }
    public int getY() { return y; }

    public ImmutablePoint translate(int dx, int dy) {
        return new ImmutablePoint(x + dx, y + dy);
    }

    public static void main(String[] args) {
        ImmutablePoint p = new ImmutablePoint(1, 2);
        ImmutablePoint q = p.translate(10, 20);
        System.out.println("p=(" + p.getX() + "," + p.getY() + ")");
        System.out.println("q=(" + q.getX() + "," + q.getY() + ")");
    }
}
