public class FinalDemo {
    static final class Constants {
        static final double PI = 3.141592;
    }

    static class Base {
        final void fixed() { System.out.println("재정의 금지"); }
        void open() { System.out.println("Base.open"); }
    }

    static class Sub extends Base {
        @Override
        void open() { System.out.println("Sub.open"); }
    }

    public static void main(String[] args) {
        Sub s = new Sub();
        s.fixed();
        s.open();
        System.out.println("PI=" + Constants.PI);
    }
}
