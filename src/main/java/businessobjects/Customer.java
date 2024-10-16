package businessobjects;

import java.util.UUID;

public class Customer {
    private String customerID;
    private String name;
    private String email;
    private String password;

    // Default constructor initializes the prepaid account and charging sessions list.
    public Customer() {
    }

    /**
     * Overloaded constructor to create a customer with initial details.
     *
     * @param name     The customer's name.
     * @param email    The customer's email address.
     * @param password The customer's password.
     */
    public Customer(String name, String email, String password) {
        this();
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
