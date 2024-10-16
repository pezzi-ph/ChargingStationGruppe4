Feature: Conducting Charging Sessions and Viewing Charging History

  As a Customer,
  I want to start a Charging Session and view past sessions,
  So that I can charge my electric vehicle and monitor my usage.

  Scenario: Starting a Charging Session using Customer ID
    Given I have sufficient balance in my Prepaid Account
    And I am at a Charging Station with Operational Status "Available"
    When I start a Charging Session using my Customer ID at the Charging Station
    Then the Charging Session begins
    And the Operational Status of the Charging Station changes to "In Use"
    And I receive a Notification that the Charging Session has started

  Scenario: Viewing past Charging Sessions with energy used, cost, and duration
    Given I am logged into my Customer account
    When I view my charging history
    Then I see details of each Charging Session including Location, Charging Mode, energy used (in kWh), cost, duration, and Invoice details
