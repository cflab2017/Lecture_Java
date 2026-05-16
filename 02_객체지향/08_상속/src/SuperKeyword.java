class Base {
    String tag() { return "[BASE]"; }
}

public class SuperKeyword extends Base {
    @Override
    String tag() {
        return super.tag() + " + " + "[SUB]";
    }

    public static void main(String[] args) {
        System.out.println(new SuperKeyword().tag());
    }
}
