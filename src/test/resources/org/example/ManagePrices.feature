Feature: Creating and Updating Prices for Charging Types

  As the Owner,
  I want to set and update prices for Charging Types at each Charging Station,
  So that I can adjust pricing based on time of day and energy types.

  Scenario: Creating prices for charging based on Charging Station and time of day
    Given I am logged in as the Owner
    When I set the price at Charging Station "Gasse 11" during "Peak Hours" for Charging Type "AC" to $0.30 per kWh
    Then the PricingModel for Charging Station "Gasse 11" during "Peak Hours" is $0.30 for "AC"
    And I set the price for Charging Type "DC" to $0.50 per kWh
    Then the PricingModel for Charging Station "Gasse 11" during "Peak Hours" is $0.50 for "DC"

  Scenario: Assigning prices to Charging Types
    Given I have a PricingModel for Charging Station "Gasse 11"
    When I assign the price to Charging Type "AC"
    And I assign a different price to Charging Type "DC"
    Then the PricingModel reflects the assigned prices for Charging Types "AC" and "DC"
