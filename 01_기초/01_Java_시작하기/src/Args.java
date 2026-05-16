public class Args {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("이름을 인자로 전달해 보세요.");
            return;
        }
        System.out.println("Hello, " + args[0] + "!");
    }
}
