public class Rectangle {
    int width;
    int height;

    Rectangle(int w, int h) {
        this.width = w;
        this.height = h;
    }

    int area() {
        return width * height;
    }

    int perimeter() {
        return 2 * (width + height);
    }

    public static void main(String[] args) {
        Rectangle r = new Rectangle(3, 4);
        System.out.println("면적=" + r.area());
        System.out.println("둘레=" + r.perimeter());
    }
}
