public class MultiCatch {
    public static void main(String[] args) {
        Object[] inputs = { "10", "abc", null };
        for (Object o : inputs) {
            try {
                String s = (String) o;
                int n = Integer.parseInt(s.trim());
                System.out.println("성공: " + n);
            } catch (NullPointerException | NumberFormatException e) {
                System.out.println("실패: " + e.getClass().getSimpleName());
            }
        }
    }
}
