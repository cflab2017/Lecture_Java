public class AbstractShape {
    static abstract class Shape {
        abstract double area();
        void describe() { System.out.println("area=" + area()); }
    }
    static class Square extends Shape {
        double side;
        Square(double s) { this.side = s; }
        @Override double area() { return side * side; }
    }
    static class Circle extends Shape {
        double r;
        Circle(double r) { this.r = r; }
        @Override double area() { return Math.PI * r * r; }
    }

    public static void main(String[] args) {
        Shape[] xs = { new Square(2), new Circle(3) };
        for (Shape s : xs) s.describe();
    }
}
