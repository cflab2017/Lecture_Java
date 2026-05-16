/** 사용자 정의 예외: 잔액 부족. */
public class Homework02 {
    static class NotEnoughBalanceException extends RuntimeException {
        public NotEnoughBalanceException(String msg) { super(msg); }
    }

    static long withdraw(long balance, long amount) {
        if (amount > balance) {
            throw new NotEnoughBalanceException("잔액 부족 " + balance + " < " + amount);
        }
        return balance - amount;
    }

    public static void main(String[] args) {
        try {
            long b1 = withdraw(1000, 300);
            System.out.println("잔액: " + b1);
            long b2 = withdraw(1000, 2000);
            System.out.println("잔액: " + b2);
        } catch (NotEnoughBalanceException e) {
            System.out.println("실패: " + e.getMessage());
        }
    }
}
