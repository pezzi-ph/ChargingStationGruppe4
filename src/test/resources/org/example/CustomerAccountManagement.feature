Feature: Registration and Management of Customer Accounts and Prepaid Accounts

  As a Customer,
  I want to register my Customer account and manage my Prepaid Account,
  So that I can use the Charging Stations.

  Scenario: Registering a new Customer account
    Given I am on the registration page
    When I provide my personal details
    Then my Customer account is created
    And I receive a unique Customer ID

  Scenario: Linking payment methods and topping up the Prepaid Account
    Given I am logged into my Customer account
    When I link my payment method
    And I perform a Balance Top-Up of $50 to my Prepaid Account
    Then my balance in the Prepaid Account increases by $50
