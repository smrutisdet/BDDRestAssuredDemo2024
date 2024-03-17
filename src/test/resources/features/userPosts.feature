Feature: Verify User Posts end Point for all supported http methods
  Scenario Outline:Verify the get method call returns proper response code and data in json format
    Given user sets the api end point with '<resource>'
    When user hits the end point with get method
    Then api should return valid '<statusCode>' in response
    And Response should be in json format
    Examples:
    |resource|statusCode|
    |posts   |200       |