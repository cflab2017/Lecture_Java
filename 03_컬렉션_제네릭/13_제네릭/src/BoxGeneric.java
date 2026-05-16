public class BoxGeneric {
    static class Box<T> {
        private T value;
        public void set(T v) { this.value = v; }
        public T get() { return value; }
    }

    public static void main(String[] args) {
        Box<String> s = new Box<>();
        s.set("Hello");
        System.out.println(s.get());

        Box<Integer> n = new Box<>();
        n.set(42);
        System.out.println(n.get() + 1);
    }
}
