/** Temperature 클래스의 캡슐화. */
public class Homework01 {
    public static void main(String[] args) {
        Temperature t = new Temperature();
        t.setCelsius(25.0);
        System.out.println(t.getCelsius() + "°C = " + t.toFahrenheit() + "°F");
        try {
            t.setCelsius(-300.0);
        } catch (IllegalArgumentException e) {
            System.out.println("거부: " + e.getMessage());
        }
    }
}

class Temperature {
    private double celsius;

    public double getCelsius() { return celsius; }

    public void setCelsius(double c) {
        if (c < -273.15) throw new IllegalArgumentException(c + "°C 는 절대영도 이하");
        this.celsius = c;
    }

    public double toFahrenheit() {
        return celsius * 9.0 / 5.0 + 32.0;
    }
}
