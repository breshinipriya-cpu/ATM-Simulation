/**
 * ATM.java
 * The core ATM machine — manages all accounts and session state.
 *
 * Design Pattern Used: Facade — hides the complexity of account
 * lookup and session management behind simple login/logout methods.
 *
 * Stores accounts in a HashMap for O(1) lookup by account number.
 */
import java.util.HashMap;

public class ATM {

    // Stores all registered accounts: key = account number, value = Account object
    private HashMap<String, Account> accounts;

    // The account currently logged in (null means no one is logged in)
    private Account currentAccount;

    /**
     * Constructor: Initializes the ATM and pre-loads demo accounts.
     * In a real system, these would be loaded from a database.
     */
    public ATM() {
        accounts       = new HashMap<>();
        currentAccount = null;

        // Seed data — two demo accounts for testing
        accounts.put("1001", new Account("1001", "1234", 5000.0));
        accounts.put("1002", new Account("1002", "5678", 10000.0));
    }

    /**
     * Attempts to log in with the given credentials.
     *
     * Steps:
     *  1. Check if account number exists in our map
     *  2. If yes, verify the PIN matches
     *  3. If both pass, set currentAccount and return true
     *
     * @param accNo - Account number entered by user
     * @param pin   - PIN entered by user
     * @return true if login succeeded, false otherwise
     */
    public boolean login(String accNo, String pin) {
        if (accounts.containsKey(accNo)) {
            Account acc = accounts.get(accNo);
            if (acc.getPin().equals(pin)) {         // PIN check (case-sensitive)
                currentAccount = acc;               // Start session
                System.out.println("Login successful! Welcome, Account " + accNo);
                return true;
            }
        }
        // Deliberately vague message — don't reveal whether account exists
        System.out.println("Invalid account number or PIN.");
        return false;
    }

    /**
     * Logs out the current user by clearing the session.
     */
    public void logout() {
        currentAccount = null;
        System.out.println("Logged out successfully.");
    }

    /**
     * Returns the currently logged-in account.
     * Returns null if nobody is logged in.
     */
    public Account getCurrentAccount() {
        return currentAccount;
    }
}