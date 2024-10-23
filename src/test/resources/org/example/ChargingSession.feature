Feature: Conducting Charging Sessions and Viewing Charging History

  As a Customer,
  I want to start a Charging Session and view past sessions,
  So that I can charge my electric vehicle and monitor my usage.

  #################################
  ########## Success Cases ########
  #################################

  Scenario: Starting a Charging Session using Customer ID
    Given I have sufficient balance in my Prepaid Account
    And I am at a Charging Point with Operational Status "Available"
    When I start a Charging Session using my Customer ID at the Charging Point
    Then the Charging Session begins
    And the Operational Status of the Charging Point changes to "In_Use"
    And I receive a Notification that the Charging Session has started

  Scenario: Viewing past Charging Sessions with energy used, cost, and duration
    Given I logged into my Customer account
    When I view my charging history
    Then I see details of each Charging Session including Location, Charging Mode, energy used (in kWh), cost, duration, and Invoice details

  #################################
  ########## Error Cases ##########
  #################################

  Scenario: Insufficient balance prevents starting a Charging Session
    Given I have insufficient balance
    And I attempt to start a Charging Session
    Then an error is shown indicating insufficient funds

  Scenario: Charging Point not operational prevents starting a Charging Session
    Given the Charging Point is not operational
    And I attempt to start a Charging Session with a not operational Charging Point
    Then an error is shown indicating the Charging Point is out of order