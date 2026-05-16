class Shape {
    double area() { return 0.0; }
}

class Square extends Shape {
    double side;
    Square(double s) { this.side = s; }
    @Override double area() { return side * side; }
}

class Circle extends Shape {
    double radius;
    Circle(double r) { this.radius = r; }
    @Override double area() { return Math.PI * radius * radius; }
}

public class MethodOverride {
    public static void main(String[] args) {
        Shape[] shapes = { new Square(3), new Circle(2) };
        for (Shape s : shapes) {
            System.out.printf("area=%.3f%n", s.area());
        }
    }
}
