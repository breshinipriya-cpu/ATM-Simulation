/**
 * Account.java
 * Represents a bank account with balance, PIN, and transaction history.
 *
 * Responsibilities:
 *  - Store account credentials (number + PIN)
 *  - Manage balance through deposit/withdraw
 *  - Keep a running list of all transactions
 */
import java.util.ArrayList;

public class Account {

    private String accountNumber;              // Unique ID for this account
    private String pin;                        // Secret PIN for login
    private double balance;                    // Current available balance
    private ArrayList<Transaction> history;    // Log of all past transactions

    /**
     * Constructor: Sets up a new account with initial values.
     *
     * @param accountNumber - Unique account identifier (e.g., "1001")
     * @param pin           - 4-digit PIN string
     * @param balance       - Starting balance in rupees
     */
    public Account(String accountNumber, String pin, double balance) {
        this.accountNumber = accountNumber;
        this.pin           = pin;
        this.balance       = balance;
        this.history       = new ArrayList<>(); // Start with empty history
    }

    // ── Getters ─────────────────────────────────────────────────────────────

    /** Returns the account number (used for login lookup). */
    public String getAccountNumber() { return accountNumber; }

    /** Returns the PIN (used by ATM to verify identity). */
    public String getPin() { return pin; }

    // ── Core Banking Operations ──────────────────────────────────────────────

    /**
     * Deposits money into this account.
     * Validates that amount is positive before proceeding.
     *
     * @param amount - Amount to deposit (must be > 0)
     */
    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount! Please enter a positive value.");
            return;
        }
        balance += amount;
        history.add(new Transaction("Deposit", amount)); // Log the transaction
        System.out.println("Deposited Rs." + amount + " successfully.");
    }

    /**
     * Withdraws money from this account.
     * Checks for both invalid input and insufficient funds.
     *
     * @param amount - Amount to withdraw (must be > 0 and <= balance)
     */
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount! Please enter a positive value.");
        } else if (amount > balance) {
            System.out.println("Insufficient balance! You have Rs." + balance);
        } else {
            balance -= amount;
            history.add(new Transaction("Withdraw", amount)); // Log the transaction
            System.out.println("Withdrawn Rs." + amount + " successfully.");
        }
    }

    /**
     * Prints the current account balance to the console.
     */
    public void checkBalance() {
        System.out.println("Current Balance: Rs." + balance);
    }

    /**
     * Prints the full transaction history.
     * Shows a message if no transactions have been made yet.
     */
    public void showHistory() {
        if (history.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            System.out.println("--- Transaction History ---");
            for (Transaction t : history) {
                System.out.println(t.getDetails()); // Each transaction prints itself
            }
        }
    }
}