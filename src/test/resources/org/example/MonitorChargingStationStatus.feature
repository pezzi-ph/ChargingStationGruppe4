Feature: Monitoring the Operational Status of Charging Stations

  As the Owner,
  I want to monitor the Operational Status of each Charging Station,
  So that I can ensure all stations are functioning properly.

  Scenario: Viewing the Operational Status of each Charging Station
    Given I am logged in as the Owner.
    When I view the Operational Status of all Charging Stations
    Then I see the Operational Status ("Available", "In Use", "Out of Order") of each Charging Station at every Location

  Scenario: Displaying the Charging Modes of Charging Stations
    Given I am viewing the Charging Stations
    When I check the Charging Mode of each Charging Station
    Then I see whether each Charging Station has the Charging Mode "AC" or "DC"
