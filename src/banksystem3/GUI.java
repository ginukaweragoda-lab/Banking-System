package banksystem3;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class GUI extends JFrame {
    private Transaction transferObject = new Transaction();
    private StringBuilder sbAllData = new StringBuilder();
    private LinkedList<Account> globalAccounts;

    private JLabel showAllData;
    private JButton showAllButton, depositButton, withdrawButton, transferButton;
    private JTextField accDeposit, depositInput;
    private JTextField accWithdraw, withdrawInput;
    private JTextField acc1Transfer, acc2Transfer, transferAmount;

    public GUI(LinkedList<Account> accounts) {
        super("Banking System");
        setLayout(null);
        globalAccounts = accounts;

        
        updateDisplayData();

        showAllData = new JLabel("<html>" + sbAllData.toString().replaceAll("\n", "<br>") + "</html>");
        showAllData.setBounds(20, 20, 450, 150);

        showAllButton = new JButton("Show All");
        showAllButton.setBounds(20, 180, 100, 30);

        depositButton = new JButton("Deposit");
        depositButton.setBounds(20, 220, 100, 30);
        accDeposit = new JTextField("Acc Num");
        accDeposit.setBounds(130, 220, 100, 30);
        depositInput = new JTextField("Amount");
        depositInput.setBounds(240, 220, 100, 30);

        withdrawButton = new JButton("Withdraw");
        withdrawButton.setBounds(20, 260, 100, 30);
        accWithdraw = new JTextField("Acc Num");
        accWithdraw.setBounds(130, 260, 100, 30);
        withdrawInput = new JTextField("Amount");
        withdrawInput.setBounds(240, 260, 100, 30);

        transferButton = new JButton("Transfer");
        transferButton.setBounds(20, 300, 100, 30);
        acc1Transfer = new JTextField("From Acc");
        acc1Transfer.setBounds(130, 300, 80, 30);
        acc2Transfer = new JTextField("To Acc");
        acc2Transfer.setBounds(220, 300, 80, 30);
        transferAmount = new JTextField("Amount");
        transferAmount.setBounds(310, 300, 80, 30);

        add(showAllData);
        add(showAllButton);
        add(depositButton); add(accDeposit); add(depositInput);
        add(withdrawButton); add(accWithdraw); add(withdrawInput);
        add(transferButton); add(acc1Transfer); add(acc2Transfer); add(transferAmount);

        HandlerClass handler = new HandlerClass();
        showAllButton.addActionListener(handler);
        depositButton.addActionListener(handler);
        withdrawButton.addActionListener(handler);
        transferButton.addActionListener(handler);
    }

    
    private void updateDisplayData() {
        sbAllData.setLength(0);
        for (Account acc : globalAccounts) {
            sbAllData.append(acc.getFirstName()).append(" ").append(acc.getLastName())
                     .append(" | Acc #: ").append(acc.getAccountNum())
                     .append(" | Balance: ").append(acc.getBalance()).append("\n");
        }
    }

    
    private void refreshDisplay() {
        updateDisplayData();
        showAllData.setText("<html>" + sbAllData.toString().replaceAll("\n", "<br>") + "</html>");
        repaint(); 
    }

    private static class Transaction {
        public boolean transfer(Account from, Account to, int amount) {
            if (from.getBalance() >= amount) {
                from.withdraw(amount);
                to.deposit(amount);
                return true;
            }
            return false;
        }
    }

    private class HandlerClass implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                if (e.getSource() == showAllButton) {
                    refreshDisplay();
                }

                if (e.getSource() == depositButton) {
                    String accText = accDeposit.getText().trim();
                    String amountText = depositInput.getText().trim();
                    
                    if (!accText.equals("Acc Num") && !amountText.equals("Amount")) {
                        int accNum = Integer.parseInt(accText);
                        int amount = Integer.parseInt(amountText);
                        
                        boolean found = false;
                        for (Account acc : globalAccounts) {
                            if (acc.getAccountNum() == accNum) {
                                acc.deposit(amount);
                                found = true;
                                break;
                            }
                        }
                        
                        if (found) {
                            refreshDisplay(); 
                            accDeposit.setText("Acc Num");
                            depositInput.setText("Amount");
                            JOptionPane.showMessageDialog(GUI.this, "Deposit successful!");
                        } else {
                            JOptionPane.showMessageDialog(GUI.this, "Account not found!");
                        }
                    }
                }

                if (e.getSource() == withdrawButton) {
                    String accText = accWithdraw.getText().trim();
                    String amountText = withdrawInput.getText().trim();
                    
                    if (!accText.equals("Acc Num") && !amountText.equals("Amount")) {
                        int accNum = Integer.parseInt(accText);
                        int amount = Integer.parseInt(amountText);
                        
                        boolean found = false;
                        for (Account acc : globalAccounts) {
                            if (acc.getAccountNum() == accNum) {
                                if (acc.getBalance() >= amount) {
                                    acc.withdraw(amount);
                                    found = true;
                                    refreshDisplay(); 
                                    accWithdraw.setText("Acc Num");
                                    withdrawInput.setText("Amount");
                                    JOptionPane.showMessageDialog(GUI.this, "Withdrawal successful!");
                                } else {
                                    JOptionPane.showMessageDialog(GUI.this, "Insufficient balance!");
                                }
                                break;
                            }
                        }
                        
                        if (!found) {
                            JOptionPane.showMessageDialog(GUI.this, "Account not found!");
                        }
                    }
                }

                if (e.getSource() == transferButton) {
                    String fromAccText = acc1Transfer.getText().trim();
                    String toAccText = acc2Transfer.getText().trim();
                    String amountText = transferAmount.getText().trim();
                    
                    if (!fromAccText.equals("From Acc") && !toAccText.equals("To Acc") && !amountText.equals("Amount")) {
                        int fromAccNum = Integer.parseInt(fromAccText);
                        int toAccNum = Integer.parseInt(toAccText);
                        int amount = Integer.parseInt(amountText);

                        Account fromAcc = null, toAcc = null;
                        for (Account acc : globalAccounts) {
                            if (acc.getAccountNum() == fromAccNum) fromAcc = acc;
                            if (acc.getAccountNum() == toAccNum) toAcc = acc;
                        }
                        
                        if (fromAcc != null && toAcc != null) {
                            if (transferObject.transfer(fromAcc, toAcc, amount)) {
                                refreshDisplay(); 
                                acc1Transfer.setText("From Acc");
                                acc2Transfer.setText("To Acc");
                                transferAmount.setText("Amount");
                                JOptionPane.showMessageDialog(GUI.this, "Transfer successful!");
                            } else {
                                JOptionPane.showMessageDialog(GUI.this, "Transfer failed: Insufficient balance!");
                            }
                        } else {
                            JOptionPane.showMessageDialog(GUI.this, "One or both accounts not found!");
                        }
                    }
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(GUI.this, "Please enter valid numbers!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(GUI.this, "An error occurred: " + ex.getMessage());
            }
        }
    }
}