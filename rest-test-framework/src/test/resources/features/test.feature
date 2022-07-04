Feature: test

  Scenario: Retrieve the first 10 Mars photos made by "Curiosity" on 1000 Martian sol.
    Given I have URL "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos" and query params "sol=1000&api_key=DEMO_KEY"
    When I send a "GET" call
    And I expect response code to be: "200"
    Then I retrieve the "10" first values

  Scenario: Retrieve the first 10 Mars photos made by "Curiosity" on Earth date equal to 1000 Martian sol.
    Given I have URL "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos" and query params "earth_date=2015-05-30&api_key=DEMO_KEY"
    When I send a "GET" call
    And I expect response code to be: "200"
    Then I retrieve the "10" first values


