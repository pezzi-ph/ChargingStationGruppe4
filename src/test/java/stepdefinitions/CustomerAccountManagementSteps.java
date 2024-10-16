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
        // Code to navigate to the registration page
        // For testing purposes, we can simulate this
        customer = new Customer();
    }

    @When("I provide my personal details")
    public void iProvideMyPersonalDetails() {
        
    }

    @Then("my Customer account is created")
    public void myCustomerAccountIsCreated() {
        
    }

    @And("I receive a unique Customer ID")
    public void iReceiveAUniqueCustomerID() {
    }
}
