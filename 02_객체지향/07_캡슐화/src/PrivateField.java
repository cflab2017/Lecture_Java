public class PrivateField {
    private int age;

    public int getAge() { return age; }

    public void setAge(int age) {
        if (age < 0) throw new IllegalArgumentException("age >= 0");
        this.age = age;
    }

    public static void main(String[] args) {
        PrivateField p = new PrivateField();
        p.setAge(21);
        System.out.println("age=" + p.getAge());
        try {
            p.setAge(-1);
        } catch (IllegalArgumentException e) {
            System.out.println("거부: " + e.getMessage());
        }
    }
}
