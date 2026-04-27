package banksystem3;
import java.io.*;
import java.util.*;

public class ReadAccounts {
    private String filePath;

    public ReadAccounts(String filePath) {
        this.filePath = filePath;
    }

    public LinkedList<String> getFirstNames() throws IOException {
        LinkedList<String> firstNames = new LinkedList<>();
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            firstNames.add(parts[0].trim());
        }
        br.close();
        return firstNames;
    }

    public LinkedList<String> getLastNames() throws IOException {
        LinkedList<String> lastNames = new LinkedList<>();
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            lastNames.add(parts[1].trim());
        }
        br.close();
        return lastNames;
    }

    public LinkedList<Integer> getAccounts() throws IOException {
        LinkedList<Integer> accounts = new LinkedList<>();
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            accounts.add(Integer.parseInt(parts[2].trim()));
        }
        br.close();
        return accounts;
    }

    public LinkedList<Integer> getBalances() throws IOException {
        LinkedList<Integer> balances = new LinkedList<>();
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            balances.add(Integer.parseInt(parts[3].trim()));
        }
        br.close();
        return balances;
    }
}