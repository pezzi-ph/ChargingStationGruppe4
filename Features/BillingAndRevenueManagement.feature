Feature: Viewing Revenue Reports and Invoices

  As the Owner,
  I want to view total billing and generate Revenue Reports,
  So that I can analyze the financial performance of the Electric Filling Station Network.

  Scenario: Viewing total billing by Location, Charging Station, and time period
    Given I am logged in as the Owner
    When I generate a Revenue Report for Location "123 Main Street" for the last month
    Then I see the total billing for each Charging Station at that Location

  Scenario: Creating basic Revenue Reports
    Given I need to review revenue
    When I generate a Revenue Report
    Then I receive a report summarizing total revenue by Location, Charging Mode, and time period
