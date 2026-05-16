public class DefaultMethod {
    interface Greeter {
        String name();
        default String greet() { return "Hello, " + name(); }
    }

    static class Korean implements Greeter {
        public String name() { return "지수"; }
    }
    static class English implements Greeter {
        public String name() { return "John"; }
        @Override
        public String greet() { return "Hi, " + name(); }
    }

    public static void main(String[] args) {
        System.out.println(new Korean().greet());
        System.out.println(new English().greet());
    }
}
