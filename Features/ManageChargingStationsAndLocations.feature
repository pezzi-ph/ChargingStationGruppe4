Feature: Managing Charging Stations and Locations

  As the Owner,
  I want to manage Charging Stations and their Locations,
  So that I can efficiently operate the Electric Filling Station Network.

  Scenario: Creating a new Location with Charging Stations
    Given I am logged in as the Owner
    When I create a new Location with address "123 Main Street"
    And I add 5 Charging Stations of type "AC"
    Then the Location "123 Main Street" is added with 5 Charging Stations of type "AC"

  Scenario: Assigning Charging Modes to Charging Stations
    Given the Location "123 Main Street" has 5 Charging Stations
    When I assign the Charging Mode "DC" to Charging Station 1
    Then Charging Station 1 at "123 Main Street" has the Charging Mode "DC"

  Scenario: Updating the Operational Status of Charging Stations
    Given Charging Station 1 at "123 Main Street" has the Operational Status "Available"
    When I set the Operational Status of Charging Station 1 to "Out of Order"
    Then Charging Station 1 at "123 Main Street" has the Operational Status "Out of Order"
