Feature: Managing Charging Stations and Point

  As the Owner,
  I want to manage Charging Stations and their Charging Points,
  So that I can efficiently operate the Electric Filling Station Network.

  Scenario: Creating a new Charging Station with Charging Points
    Given I am logged in as the Owner
    When I create a new Charging Station with location "123 Main Street"
    And I add 5 Charging Points of type "AC"
    Then the Charging Station "123 Main Street" is added with 5 Charging Points of type "AC"

  Scenario: Assigning Charging Modes to Charging Points
    Given the Charging Station "123 Main Street" has 5 Charging Points
    When I assign the Charging Mode "DC" to Charging Point 1
    Then Charging Point 1 at "123 Main Street" has the Charging Mode "DC"

  Scenario: Updating the Operational Status of Charging Points
    Given Charging Point 1 at "123 Main Street" has the Operational Status "Available"
    When I set the Operational Status of Charging Point 1 to "Out of Order"
    Then Charging Point 1 at "123 Main Street" has the Operational Status "Out of Order"
