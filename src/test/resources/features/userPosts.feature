Feature: Verify User Posts end Point for all supported http methods
 ###################GET
#  Scenario Outline:Verify the get method call returns proper response code and data in json format
#    Given user sets the api end point with '<resource>'
#    When user hits the end point with get method
#    Then api should return valid '<statusCode>' in response
#    And Response should be in json format
#    Examples:
#    |resource|statusCode|
#    |posts   |200       |
#############POST
#    Scenario Outline: Create new user post though http post method
#      Given user sets the api end point with '<resource>'
#      When user hits the end point with post method by passing valid '<id>' and '<title>' and '<author>'
#      Then api should return valid '<statusCode>' in response
#      And API should return created data in response body with '<id>' and '<title>' and '<author>'
#      And Response should be in json format
#      Examples:
#        | resource |id|title                       |author|statusCode|
#         |posts    |33|Transforming from QA to SDET|Smruti|201        |

################PUT
#  Scenario Outline: Update an user post through http put method
#    Given user sets the api end point with '<resource>'
#    When user hits the end point with put method by passing valid '<id>' and '<title>' and '<author>'
#    Then api should return valid '<statusCode>' in response
#    And API should return updated data in response body with '<id>' and '<title>' and '<author>'
#    And Response should be in json format
#    Examples:
#      | resource |id|title                       |author|statusCode|
#      |posts    |33|RestAssured Framework Designing|qaautomationclasses-Smruti|200|

#######PATCH
  Scenario Outline: Update an user post through http PATCH method
    Given user sets the api end point with '<resource>'
    When user hits the end point with patch method by passing valid '<id>'  and '<author>'
    Then api should return valid '<statusCode>' in response
    And API should return updated patch data in response body with '<id>' and '<author>'
    And Response should be in json format
    Examples:
      | resource |id|author|statusCode|
      |posts    |33|Smrutiranjan|200|