public class StaticVsInstance {
    static int staticCount;
    int instanceCount;

    void increment() {
        staticCount++;
        instanceCount++;
    }

    public static void main(String[] args) {
        StaticVsInstance a = new StaticVsInstance();
        StaticVsInstance b = new StaticVsInstance();
        a.increment(); a.increment();
        b.increment();
        System.out.println("a.instance=" + a.instanceCount);
        System.out.println("b.instance=" + b.instanceCount);
        System.out.println("static=" + StaticVsInstance.staticCount);
    }
}
