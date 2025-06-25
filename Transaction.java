
public  class Transaction {

    public static void transfer(Account fromAccount, Account toAccount, double amount) {
        if (fromAccount.getBalance() >= amount && amount > 0) {
            fromAccount.withdraw(amount);
            toAccount.deposit(amount);
        } else {
            System.out.println("Transfer failed. Check the balance or amount.");
        }
    }
        
    }