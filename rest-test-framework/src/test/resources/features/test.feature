Feature: test

  Scenario: test
    Given I send a GET call to: "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=1000&api_key=DEMO_KEY"
    And I expect response code to be: "200" 