/**
 * ATMSimulation.java
 * Entry point of the ATM application.
 *
 * Wires together the ATM engine and Menu UI,
 * then hands control to the user via menu.start().
 *
 * This is the ONLY class with a main() method —
 * keeping startup logic separate from business logic.
 */
public class ATMSimulation {

    public static void main(String[] args) {
        ATM  atm  = new ATM();       // Create the ATM machine (loads accounts)
        Menu menu = new Menu(atm);   // Attach the UI menu to it
        menu.start();                // Begin the user session
    }
}