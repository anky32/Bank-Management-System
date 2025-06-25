
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class GUI extends JFrame {
    private Transaction transferObject;
    private StringBuilder sbAllData;
    private LinkedList<Account> globalAccounts;

    private JTable accountTable;
    private DefaultTableModel tableModel;
    private JButton showAllButton;
    private JButton depositButton;
    private JButton withdrawButton;
    private JButton transferButton;

    private JTextField accDeposit;
    private JTextField accWithdraw;
    private JTextField acc1Transfer;
    private JTextField acc2Transfer;

    private JTextField depositInput;
    private JTextField withdrawInput;
    private JTextField transferAmount;

    public GUI(LinkedList<Account> accounts) {
        super("Banking System");
        setLayout(null);

        globalAccounts = accounts;
        sbAllData = new StringBuilder();

        String[] columnNames = {"Account Number", "First Name", "Last Name", "Balance"};
        tableModel = new DefaultTableModel(columnNames, 0);
        accountTable = new JTable(tableModel);

        updateTableData();

        showAllButton = new JButton("Show All");
        depositButton = new JButton("Deposit");
        withdrawButton = new JButton("Withdraw");
        transferButton = new JButton("Transfer");

        showAllButton.setBounds(50, 50, 150, 30);
        depositButton.setBounds(50, 100, 150, 30);
        withdrawButton.setBounds(50, 150, 150, 30);
        transferButton.setBounds(50, 200, 150, 30);

        accDeposit = new JTextField();
        accWithdraw = new JTextField();
        acc1Transfer = new JTextField();
        acc2Transfer = new JTextField();
        depositInput = new JTextField();
        withdrawInput = new JTextField();
        transferAmount = new JTextField();

        accDeposit.setBounds(250, 100, 150, 30);
        depositInput.setBounds(450, 100, 150, 30);
        accWithdraw.setBounds(250, 150, 150, 30);
        withdrawInput.setBounds(450, 150, 150, 30);
        acc1Transfer.setBounds(250, 200, 150, 30);
        acc2Transfer.setBounds(450, 200, 150, 30);
        transferAmount.setBounds(650, 200, 150, 30);

        JScrollPane scrollPane = new JScrollPane(accountTable);
        scrollPane.setBounds(50, 250, 800, 300);
        add(showAllButton);
        add(depositButton);
        add(withdrawButton);
        add(transferButton);
        add(accDeposit);
        add(depositInput);
        add(accWithdraw);
        add(withdrawInput);
        add(acc1Transfer);
        add(acc2Transfer);
        add(transferAmount);
        add(scrollPane);
        HandlerClass handler = new HandlerClass();
        showAllButton.addActionListener(handler);
        depositButton.addActionListener(handler);
        withdrawButton.addActionListener(handler);
        transferButton.addActionListener(handler);

        setSize(1000, 1000);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void updateTableData() {
        tableModel.setRowCount(0); 
        for (Account account : globalAccounts) {
            Object[] rowData = {
                account.getAccountNumber(),
                account.getFirstName(),
                account.getLastName(),
                account.getBalance()
            };
            tableModel.addRow(rowData);
        }
    }

    private class HandlerClass implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == showAllButton) {
                updateTableData();
            } else if (e.getSource() == depositButton) {
                String accountNumber = accDeposit.getText();
                double amount = Double.parseDouble(depositInput.getText());

                for (Account account : globalAccounts) {
                    if (account.getAccountNumber().equals(accountNumber)) {
                        account.deposit(amount);
                        updateTableData();
                        break;
                    }
                }
            } else if (e.getSource() == withdrawButton) {
                String accountNumber = accWithdraw.getText();
                double amount = Double.parseDouble(withdrawInput.getText());

                for (Account account : globalAccounts) {
                    if (account.getAccountNumber().equals(accountNumber)) {
                        account.withdraw(amount);
                        updateTableData();
                        break;
                    }
                }
            } else if (e.getSource() == transferButton) {
                String acc1 = acc1Transfer.getText();
                String acc2 = acc2Transfer.getText();
                double amount = Double.parseDouble(transferAmount.getText());

                Account fromAccount = null;
                Account toAccount = null;

                for (Account account : globalAccounts) {
                    if (account.getAccountNumber().equals(acc1)) {
                        fromAccount = account;
                    }
                    if (account.getAccountNumber().equals(acc2)) {
                        toAccount = account;
                    }
                }

                if (fromAccount != null && toAccount != null) {
                    Transaction.transfer(fromAccount, toAccount, amount);
                    updateTableData();
                }
            }
        }
    }
}