@MyTag
Feature: Create a Route

  Scenario: Viewing the different routes informations


Given User goes to the website
When User clicks the directions button
And  User determines the starting and destination
And  User click on search button
And User creates route by car
And User displays estimated time and distance
And User creates route with public transport
And User displays public transport route info
And User determines walking route to destination
And User lists the results
And User searches for a new route
And User lists restorants close to the route
And User closes the map window and exits the page.
Then User verifies that they have logged out successfully