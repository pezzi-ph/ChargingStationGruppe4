Feature: Monitoring the Status of Charging Points

  As the Owner,
  I want to monitor the Status of each Charging Point,
  So that I can ensure all Points are functioning properly.
  Background:
    Given I logged in as the Station Owner.


  Scenario: Viewing the Status of each Charging Point
    Given I logged in as the Station Owner.
    When I view the Status of all Charging Points
    Then I see the Status ("AVAILABLE", "IN_USE", "OUT_OF_ORDER") of each Charging Point at every Location

  Scenario: Displaying the Charging Types of Charging Points
    Given I am viewing the Charging Points
    When I check the Charging Type of each Charging Point
    Then I see whether each Charging Point has the Charging Type "AC" or "DC"
