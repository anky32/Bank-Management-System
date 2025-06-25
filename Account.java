public class Account extends Customer {
    private String accountNumber;
    private double balance;

    public Account (String firstName, String lastName, String accountNumber, double balance) {
        super(firstName, lastName);
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public String getAccountNumber(){
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if(amount > 0) {
            balance += amount;
        }
    }

    public void withdraw(double amount) {
        if(amount > 0 && balance >= amount) {
            balance -= amount;
        }
    }
} 

