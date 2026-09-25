package exercises;
public class Exercise08_BankingConsole {
    static class Account {
        private double balance;
        Account(double openingBalance) { balance = openingBalance; }
        void deposit(double amount) { if (amount > 0) balance += amount; }
        boolean withdraw(double amount) {
            if (amount <= 0 || amount > balance) return false;
            balance -= amount; return true;
        }
        double getBalance() { return balance; }
    }
    public static void main(String[] args) {
        Account account = new Account(1000);
        account.deposit(500);
        System.out.println("Withdrawal successful: " + account.withdraw(300));
        System.out.println("Balance: " + account.getBalance());
    }
}
