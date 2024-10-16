package stepdefinitions;

import businessobjects.Customer;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerAccountManagementSteps {
    private Customer customer;
    private boolean accountCreated;

    @Given("I am on the registration page")
    public void iAmOnTheRegistrationPage() {
        customer = new Customer();
    }

    @When("I provide my personal details")
    public void iProvideMyPersonalDetails() {
        customer.setName("Jane Smith");
        customer.setEmail("jane.smith@example.com");
        customer.setPassword("SecurePassword123");
        boolean accountCreated = customer.registerAccount();
        assertTrue(accountCreated, "Customer account should be created");
    }

    @Then("my Customer account is created")
    public void myCustomerAccountIsCreated() {
        assertNotNull(customer.getCustomerId(), "Customer ID should not be null");
    }

    @And("I receive a unique Customer ID")
    public void iReceiveAUniqueCustomerID() {
        System.out.println("Customer ID: " + customer.getCustomerId());
    }

    // Scenario 2: Linking payment methods and topping up the Prepaid Account
    @Given("I am logged into my Customer account")
    public void iAmLoggedIntoMyCustomerAccount() {
        // Simulate login
        customer = new Customer();
        customer.setName("Max Mustermann");
        customer.setEmail("max.m@google.com");
        customer.setPassword("MaxMusterMann");
        accountCreated = customer.registerAccount();
        assertTrue(accountCreated, "Customer account should be created");
    }

    @When("I link my payment method")
    public void iLinkMyPaymentMethod() {

    }

    @And("I perform a Balance Top-Up of ${int} to my Prepaid Account")
    public void iPerformABalanceTopUpOf$ToMyPrepaidAccount(int arg0) {

    }

    @Then("my balance in the Prepaid Account increases by ${int}")
    public void myBalanceInThePrepaidAccountIncreasesBy$(int arg0) {
    }
}
