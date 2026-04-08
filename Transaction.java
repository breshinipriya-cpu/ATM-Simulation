/**
 * Transaction.java
 * Represents a single banking transaction (deposit or withdrawal).
 * Acts as an immutable record/log entry for account history.
 *
 * Design Pattern Used: Value Object — once created, it never changes.
 */
public class Transaction {

    private String type;   // "Deposit" or "Withdraw"
    private double amount; // Amount involved in this transaction

    /**
     * Constructor: Creates a new transaction record.
     *
     * @param type   - Type of transaction (e.g., "Deposit", "Withdraw")
     * @param amount - The money amount involved
     */
    public Transaction(String type, double amount) {
        this.type   = type;
        this.amount = amount;
    }

    /**
     * Returns a human-readable summary of this transaction.
     * Example output: "Deposit: Rs.500.0"
     */
    public String getDetails() {
        return type + ": Rs." + amount;
    }
}