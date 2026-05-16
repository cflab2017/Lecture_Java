/** Greeting 인터페이스. */
public class Homework01 {
    public static void main(String[] args) {
        Greeting[] gs = { new KoreanG(), new EnglishG() };
        for (Greeting g : gs) System.out.println(g.hello());
        for (Greeting g : gs) System.out.println(g.shout());
    }
}

interface Greeting {
    String hello();
    default String shout() { return hello().toUpperCase(); }
}

class KoreanG implements Greeting {
    @Override public String hello() { return "안녕하세요"; }
}

class EnglishG implements Greeting {
    @Override public String hello() { return "Hello"; }
}
