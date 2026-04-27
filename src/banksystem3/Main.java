package banksystem3;
import java.util.*;
import java.io.*;
import javax.swing.JFrame; // Required import to use JFrame.EXIT_ON_CLOSE

public class Main {
    public static void main(String[] args) {
        try {
            ReadAccounts read = new ReadAccounts("Accounts.csv");

            LinkedList<String> firstNames = read.getFirstNames();
            LinkedList<String> lastNames = read.getLastNames();
            LinkedList<Integer> accountNums = read.getAccounts();
            LinkedList<Integer> balances = read.getBalances();

            LinkedList<Account> accounts = new LinkedList<>();

            for (int i = 0; i < firstNames.size(); i++) {
                accounts.add(new Account(firstNames.get(i), lastNames.get(i), accountNums.get(i), balances.get(i)));
            }

            GUI gui = new GUI(accounts);
            gui.setSize(600, 500);
            gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            gui.setVisible(true);

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
