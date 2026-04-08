/**
 * Menu.java
 * Handles all user interaction — the "UI layer" of the ATM.
 *
 * Responsibilities:
 *  - Prompt user for login credentials
 *  - Display menu options in a loop
 *  - Route choices to the correct Account methods
 *  - Exit cleanly when user logs out
 *
 * BUG FIX vs original: Added sc.nextLine() after sc.nextInt()/sc.nextDouble()
 * to consume the leftover newline — prevents input-skipping bugs.
 */
import java.util.Scanner;

public class Menu {

    private ATM     atm; // Reference to the ATM engine (business logic)
    private Scanner sc;  // Single shared Scanner for all input

    /**
     * Constructor: Binds this menu to an ATM instance.
     *
     * @param atm - The ATM object that manages accounts and sessions
     */
    public Menu(ATM atm) {
        this.atm = atm;
        this.sc  = new Scanner(System.in);
    }

    /**
     * Entry point — starts the ATM session flow:
     *  1. Prompt for credentials → login
     *  2. Show menu in a loop until logout (choice == 5)
     *  3. Close the scanner on exit
     */
    public void start() {
        System.out.println("===== Welcome to ATM =====");

        // ── Step 1: Login ────────────────────────────────────────────────────
        System.out.print("Enter Account Number: ");
        String accNo = sc.nextLine();

        System.out.print("Enter PIN: ");
        String pin = sc.nextLine();

        // If credentials are wrong, stop immediately
        if (!atm.login(accNo, pin)) {
            sc.close();
            return;
        }

        // ── Step 2: Main Menu Loop ────────────────────────────────────────────
        int choice;
        do {
            printMenu();

            System.out.print("Choose: ");
            choice = sc.nextInt();
            sc.nextLine(); // ← FIX: flush the leftover '\n' from nextInt()

            Account acc = atm.getCurrentAccount(); // Get the logged-in account

            // Route each choice to the right operation
            switch (choice) {
                case 1:
                    // Check Balance
                    acc.checkBalance();
                    break;

                case 2:
                    // Deposit
                    System.out.print("Enter deposit amount: Rs.");
                    double depositAmt = sc.nextDouble();
                    sc.nextLine(); // flush newline
                    acc.deposit(depositAmt);
                    break;

                case 3:
                    // Withdraw
                    System.out.print("Enter withdraw amount: Rs.");
                    double withdrawAmt = sc.nextDouble();
                    sc.nextLine(); // flush newline
                    acc.withdraw(withdrawAmt);
                    break;

                case 4:
                    // Transaction History
                    acc.showHistory();
                    break;

                case 5:
                    // Logout — loop will exit since choice == 5
                    atm.logout();
                    break;

                default:
                    System.out.println("Invalid option. Please choose 1–5.");
            }

        } while (choice != 5); // Keep looping until logout

        sc.close(); // Always close the Scanner to free resources
    }

    /**
     * Helper: Prints the menu options neatly.
     * Extracted so the do-while loop stays readable.
     */
    private void printMenu() {
        System.out.println("\n--- Menu ---");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Transaction History");
        System.out.println("5. Logout");
    }
}