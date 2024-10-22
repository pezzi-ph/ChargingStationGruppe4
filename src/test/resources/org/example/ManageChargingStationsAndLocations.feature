Feature: Managing Charging Stations and Point

  As the Owner,
  I want to manage Charging Stations and their Charging Points,
  So that I can efficiently operate the Electric Filling Station Network.

  Background:
    Given I logged in as the Owner

  Scenario: Creating a new Charging Station with Charging Point
    When I create a new Charging Station with location "123 Main Street"
    And I add 5 Charging Points of type "AC"
    Then the Charging Station "123 Main Street" is added with 5 Charging Points of type "AC"

  Scenario: Assigning Charging Types to Charging Points
    Given the Charging Station "123 Main Street" has 5 Charging Points
    When I assign the Charging Type "DC" to Charging Point 1
    Then Charging Point 1 at "123 Main Street" has the Charging Type "DC"

  Scenario: Updating the Operational Status of Charging Points
    Given Charging Point 1 at "123 Main Street" has the Operational Status "AVAILABLE"
    When I set the Operational Status of Charging Point 1 to "OUT_OF_ORDER"
    Then Charging Point 1 at "123 Main Street" has the new Operational Status "OUT_OF_ORDER"

  Scenario:
    When I create a new Charging Station with location "Linz"
    And I add 3 Charging Points of type "AC"
    Then the Charging Station "Linz" is added with 3 Charging Points of type "AC"

  #################################
  ########## Error Cases ##########
  #################################
  Scenario: Assigning Charging Types to Charging Points
    Given the Charging Station "123 Main Street" has 5 Charging Points
    When I assign the Charging Type "DC" to Charging Point 1
    Then Charging Point 1 at "123 Main Street" doesn't have the Charging Type "AC"

  Scenario:
    When I create a new Charging Station with location "Linz"
    And I add 3 Charging Points of type "AC"
    Then the Charging Station "Linz" isn't added with 4 Charging Points of type "DC"
