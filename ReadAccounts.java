
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadAccounts {
    private List<String> firstName = new ArrayList<>();
    private List<String> lastName = new ArrayList<>();
    private List<String> accountNumbers = new ArrayList<>();
    private List<Double> balances = new ArrayList<>();


    public ReadAccounts(String filepath) {
        try(BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                firstName.add(values[0]);
                lastName.add(values[1]);
                accountNumbers.add(values[2]);
                balances.add(Double.parseDouble(values[3]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getFirstNames() {
        return firstName;
    }

    public List<String> getLastNames() {
        return lastName;
    }

    public List<String> getAccountNumbers() {
        return accountNumbers;
    }

    public List<Double> getBalances() {
        return balances;
    }

}