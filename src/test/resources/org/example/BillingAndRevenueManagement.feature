Feature: Viewing Revenue Reports and Invoices

  As the Owner,
  I want to view total billing and generate Revenue Reports,
  So that I can analyze the financial performance of the Electric Filling Station Network.

  Background:
    Given I am logged in as the Station Owner

  Scenario: Viewing total billing by Location, Charging Station, and time period
    Given I need to review revenue
    When I generate a Revenue Report for Location "123 Main Street" for the last month
    Then I see the total billing for each Charging Station at that Location

  Scenario: Creating basic Revenue Reports
    Given I need to review revenue
    When I generate a Revenue Report
    Then I receive a report summarizing total revenue by Location, Charging Mode, and time period

  #################################
  ########## Error Cases ##########
  #################################

  Scenario: Viewing Revenue Report without being logged in
    Given I am not logged in as the Station Owner
    When I attempt to generate a Revenue Report for Location "123 Main Street"
    Then I receive an error message "Please log in to view revenue reports"

  Scenario: Viewing Revenue Report for a time period with no data
    Given I am logged in as the Station Owner
    When I generate a Revenue Report for Location "123 Main Street" for next month
    Then I see a message "No revenue data available for the selected time period"
