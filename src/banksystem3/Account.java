package banksystem3;
public class Account extends Customer {
    private int accountNumber;
    private int balance;

    public Account(String fName, String lName, int accNum, int balance) {
        setFirstName(fName);
        setLastName(lName);
        this.accountNumber = accNum;
        this.balance = balance;
    }

    public int getAccountNum() {
        return accountNumber;
    }

    public int getBalance() {
        return balance;
    }

    public void deposit(int amount) {
        balance += amount;
    }

    public void withdraw(int amount) {
        balance -= amount;
    }
}