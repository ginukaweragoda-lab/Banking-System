package banksystem3;
public class Transaction {
    public void transfer(Account from, Account to, int amount) {
        from.withdraw(amount);
        to.deposit(amount);
    }
}