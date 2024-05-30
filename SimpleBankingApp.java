import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SimpleBankingApp extends JFrame {
    private double balance;
    private JTextField amountField;
    private JLabel balanceLabel;
    private JPasswordField pinField;
    private JButton depositButton, withdrawButton, viewHistoryButton, changePinButton;
    private String pin;
    private ArrayList<String> transactionHistory;

    public SimpleBankingApp() {
        balance = 1000.0; // Default balance
        pin = "1234"; // Default PIN
        transactionHistory = new ArrayList<>();
        createLoginUI();
    }

    private void createLoginUI() {
        setTitle("Banking Application - Login");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel pinLabel = new JLabel("Enter PIN:");
        pinLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(pinLabel, gbc);

        pinField = new JPasswordField(10);
        pinField.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(pinField, gbc);

        JButton loginButton = new JButton("Login");
        loginButton.setBackground(new Color(60, 179, 113));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(loginButton, gbc);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String enteredPin = new String(pinField.getPassword());
                if (enteredPin.equals(pin)) {
                    createBankingUI();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid PIN. Try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        setVisible(true);
    }

    private void createBankingUI() {
        getContentPane().removeAll();
        revalidate();
        repaint();

        setTitle("Banking Application");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel currentBalanceLabel = new JLabel("Current Balance:");
        currentBalanceLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(currentBalanceLabel, gbc);

        balanceLabel = new JLabel("₹" + balance);
        balanceLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(balanceLabel, gbc);

        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(amountLabel, gbc);

        amountField = new JTextField(10);
        amountField.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(amountField, gbc);

        depositButton = new JButton("Deposit");
        depositButton.setBackground(new Color(100, 149, 237));
        depositButton.setForeground(Color.WHITE);
        depositButton.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(depositButton, gbc);

        withdrawButton = new JButton("Withdraw");
        withdrawButton.setBackground(new Color(220, 20, 60));
        withdrawButton.setForeground(Color.WHITE);
        withdrawButton.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(withdrawButton, gbc);

        viewHistoryButton = new JButton("View History");
        viewHistoryButton.setBackground(new Color(255, 165, 0));
        viewHistoryButton.setForeground(Color.WHITE);
        viewHistoryButton.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(viewHistoryButton, gbc);

        changePinButton = new JButton("Change PIN");
        changePinButton.setBackground(new Color(0, 128, 128));
        changePinButton.setForeground(Color.WHITE);
        changePinButton.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(changePinButton, gbc);

        // Action Listeners
        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deposit();
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                withdraw();
            }
        });

        viewHistoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewTransactionHistory();
            }
        });

        changePinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePin();
            }
        });

        setVisible(true);
    }

    private void deposit() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            if (amount > 0) {
                balance += amount;
                balanceLabel.setText("₹" + balance);
                transactionHistory.add("Deposited: ₹" + amount);
                amountField.setText("");
                JOptionPane.showMessageDialog(this, "Successfully deposited ₹" + amount, "Deposit",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Please enter a positive amount.", "Invalid Input",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number.", "Invalid Input",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void withdraw() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            if (amount > 0 && amount <= balance) {
                balance -= amount;
                balanceLabel.setText("₹" + balance);
                transactionHistory.add("Withdrew: ₹" + amount);
                amountField.setText("");
                JOptionPane.showMessageDialog(this, "Successfully withdrew ₹" + amount, "Withdraw",
                        JOptionPane.INFORMATION_MESSAGE);
            } else if (amount > balance) {
                JOptionPane.showMessageDialog(this, "Insufficient balance.", "Invalid Input",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Please enter a positive amount.", "Invalid Input",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number.", "Invalid Input",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void viewTransactionHistory() {
        if (transactionHistory.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No transactions yet.", "Transaction History",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            StringBuilder history = new StringBuilder("Transaction History:\n");
            for (String transaction : transactionHistory) {
                history.append(transaction).append("\n");
            }
            JOptionPane.showMessageDialog(this, history.toString(), "Transaction History",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void changePin() {
        String newPin = JOptionPane.showInputDialog(this, "Enter new PIN:", "Change PIN", JOptionPane.PLAIN_MESSAGE);
        if (newPin != null && newPin.length() == 4 && newPin.matches("\\d+")) {
            pin = newPin;
            JOptionPane.showMessageDialog(this, "PIN changed successfully.", "Change PIN",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Invalid PIN. Please enter a 4-digit number.", "Change PIN",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SimpleBankingApp();
            }
        });
    }
}
