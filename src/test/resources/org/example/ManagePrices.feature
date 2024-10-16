Feature: Creating and Updating Prices for Charging Types

  As the Owner,
  I want to set and update prices for Charging Types at each Chargin Station,
  So that I can adjust pricing based on time of day and energy types.

  Scenario: Creating prices for charging based on Chargin Station and time of day
    Given I am logged in as the Owner
    When I set the price at Charging Station "Gasse 11" during "Peak Hours" for Charging Type "AC" to $0.30 per kWh
    And I set the price for Charging Type "DC" to $0.50 per kWh
    Then the PricingModel for Chargin Station "Gasse 11" during "Peak Hours" is updated

  Scenario: Assigning prices to Charging Types
    Given I have a PricingModel for Charging Station "Gasse 11"
    When I assign the price to Charging Type "AC"
    And I assign a different price to Charging Type "DC"
    Then the PricingModel reflects the assigned prices for Charging Types "AC" and "DC"
