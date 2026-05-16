/** Shape 계층 면적. */
public class Homework02 {
    public static void main(String[] args) {
        ShapeHW[] shapes = { new SquareHW(3), new TriangleHW(4, 3) };
        for (ShapeHW s : shapes) {
            System.out.println(s.getClass().getSimpleName().replace("HW", "")
                    + ": " + s.area());
        }
    }
}

class ShapeHW {
    double area() { return 0.0; }
}

class SquareHW extends ShapeHW {
    double side;
    SquareHW(double s) { this.side = s; }
    @Override double area() { return side * side; }
}

class TriangleHW extends ShapeHW {
    double base, height;
    TriangleHW(double base, double height) { this.base = base; this.height = height; }
    @Override double area() { return base * height / 2.0; }
}
