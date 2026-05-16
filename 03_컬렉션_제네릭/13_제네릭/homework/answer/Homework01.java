/** Pair<A,B> 제네릭 클래스. */
public class Homework01 {
    static class Pair<A, B> {
        private final A first;
        private final B second;

        Pair(A first, B second) {
            this.first = first;
            this.second = second;
        }

        A first() { return first; }
        B second() { return second; }

        @Override
        public String toString() {
            return "(" + first + ", " + second + ")";
        }
    }

    public static void main(String[] args) {
        Pair<Integer, String> p1 = new Pair<>(1, "one");
        Pair<String, Double> p2 = new Pair<>("k", 3.14);
        System.out.println(p1);
        System.out.println(p2);
    }
}
