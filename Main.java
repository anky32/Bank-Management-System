import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {

        ReadAccounts reader = new ReadAccounts("Accounts.csv");


        LinkedList<Account> accounts = new LinkedList<>();

        for (int i =0; i < reader.getFirstNames().size(); i++) {
            accounts.add(new Account(
                reader.getFirstNames().get(i),
                reader.getLastNames().get(i),
                reader.getAccountNumbers().get(i),
                reader.getBalances().get(i)
            ));

        }
        GUI gui = new GUI(accounts);
    }
    
}