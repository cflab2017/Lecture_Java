/** Circle 면적 계산. */
public class Homework02 {
    public static void main(String[] args) {
        double[] radii = {1.0, 2.5, 3.0};
        for (double r : radii) {
            Circle c = new Circle(r);
            System.out.printf("r=%.3f area=%.3f%n", r, c.area());
        }
    }
}

class Circle {
    double radius;

    Circle(double radius) {
        this.radius = radius;
    }

    double area() {
        return Math.PI * radius * radius;
    }
}
