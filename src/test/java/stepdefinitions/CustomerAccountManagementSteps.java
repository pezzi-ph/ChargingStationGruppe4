package stepdefinitions;

import businessobjects.Customer;
import businessobjects.PrepaidAccount;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerAccountManagementSteps {
    private Customer customer;
    private boolean accountCreated;
    private PrepaidAccount prepaidAccount;

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

        prepaidAccount = new PrepaidAccount();
    }

    @When("I link my payment method")
    public void iLinkMyPaymentMethod() throws Exception {
        try
        {
            boolean paymentLinked = customer.linkPaymentMethod("Credit Card", "1234-5678-9101-1121", "12/25", "000");
        }
        catch(Exception e)
        {
            fail();
        }
    }

    @And("I perform a Balance Top-Up of ${int} to my Prepaid Account")
    public void iPerformABalanceTopUpOf$ToMyPrepaidAccount(int amount) throws Exception {
        try
        {
            prepaidAccount.topUpBalance(amount);
        }
        catch (Exception e) {
            errorMessage = e.getMessage();
        }
    }

    @Then("my balance in the Prepaid Account increases by ${int}")
    public void myBalanceInThePrepaidAccountIncreasesBy$(int amount) {
        double expectedBalance = prepaidAccount.getBalance();
        assertEquals(expectedBalance, amount, "Prepaid account balance should match the topped-up amount.");
    }

    String errorMessage;
    @When("I attempt to link an invalid payment method")
    public void iAttemptToLinkAnInvalidPaymentMethod() {
        try
        {
            boolean paymentLinked = customer.linkPaymentMethod("Credit Card", "invalid-card-number", "12/25", "000");
        }
        catch(Exception e)
        {
            errorMessage = e.getMessage();
        }
    }


}
