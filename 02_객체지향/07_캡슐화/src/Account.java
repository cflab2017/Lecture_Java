public class Account {
    private long balance;

    public Account(long initial) { this.balance = initial; }

    public long getBalance() { return balance; }

    public void deposit(long amount) {
        if (amount <= 0) throw new IllegalArgumentException("amount > 0");
        balance += amount;
    }

    public void withdraw(long amount) {
        if (amount <= 0) throw new IllegalArgumentException("amount > 0");
        if (amount > balance) throw new IllegalStateException("잔액 부족");
        balance -= amount;
    }

    public static void main(String[] args) {
        Account a = new Account(10_000);
        a.deposit(5_000);
        a.withdraw(3_000);
        System.out.println("잔액=" + a.getBalance());
    }
}
