/** Vehicle 다형성. */
public class Homework01 {
    public static void main(String[] args) {
        Vehicle[] vs = { new Car(), new Bike() };
        for (Vehicle v : vs) {
            System.out.println(v.describe());
        }
    }
}

abstract class Vehicle {
    abstract String describe();
}

class Car extends Vehicle {
    @Override String describe() { return "Car 4 wheels"; }
}

class Bike extends Vehicle {
    @Override String describe() { return "Bike 2 wheels"; }
}
