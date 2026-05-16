public class BreakContinue {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            if (i == 3) continue;
            if (i == 6) break;
            System.out.print(i + " ");
        }
        System.out.println();

        outer:
        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 3; k++) {
                if (i + k == 3) break outer;
                System.out.print("(" + i + "," + k + ") ");
            }
        }
        System.out.println();
    }
}
