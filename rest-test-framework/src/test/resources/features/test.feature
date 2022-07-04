Feature: pilot Java test framework for testing NASA's open API

  Scenario: Retrieve the first 10 Mars photos made by "Curiosity" on 1000 Martian sol.
    Given I have URL "{{url}}" and query params "{{marsQuery}}"
    When I send a "GET" call
    And I expect response code to be: "200"
    Then I retrieve the "10" first values for "Mars"

  Scenario: Retrieve the first 10 Mars photos made by "Curiosity" on Earth date equal to 1000 Martian sol.
    Given I have URL "{{url}}" and query params "{{earthQuery}}"
    When I send a "GET" call
    And I expect response code to be: "200"
    Then I retrieve the "10" first values for "Earth"

  Scenario: Retrieve and compare the first 10 Mars photos made by "Curiosity" on 1000 sol and on Earth date equal to 1000 Martian sol.
    Given I have URL "{{url}}" and query params "{{marsQuery}}"
    When I send a "GET" call
    And I expect response code to be: "200"
    Then I retrieve the "10" first values for "Mars"

    Given I have URL "{{url}}" and query params "{{earthQuery}}"
    When I send a "GET" call
    And I expect response code to be: "200"
    Then I retrieve the "10" first values for "Earth"

    Then I compare "Mars" to "Earth"

  Scenario: Validate that the amounts of pictures that each "Curiosity" camera took on 1000 Mars sol is not greater than 10 times the amount taken by other cameras on the same date.
    Given I have URL "{{url}}" and query params "{{marsQuery}}"
    When I send a "GET" call
    And I expect response code to be: "200"
    Then I validate if camera is greater than others
