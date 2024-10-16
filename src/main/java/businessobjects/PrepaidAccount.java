package businessobjects;

public class PrepaidAccount {

    private double balance;

    public PrepaidAccount() {
        this.balance = 0.0;
    }

    public void topUpBalance(double amount) {
        if (amount > 0) {
            balance += amount;
        }
        // Fehler bei negativen Values
    }

    public double getBalance() {
        return balance;
    }

    public boolean hasSufficientBalance(double amount) {
        return balance >= amount;
    }

    public void deductAmount(double amount) {
        if (hasSufficientBalance(amount)) {
            balance -= amount;
        }
        // insufficient balance or throw an exception
    }
}
