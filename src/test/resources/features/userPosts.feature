Feature: Verify User Posts end Point for all supported http method

  Background:
    Given user sets the api end point with "posts"
#####################################GET###########################################################
  Scenario Outline:Verify the get method call returns proper response code and data in json format
    When user hits the end point with get method
    Then api should return valid '<statusCode>' in response
    And Response should be in json format
    Examples:
      | statusCode |
      | 200        |
#########################################POST########################################################
  Scenario Outline: Create new user post though http post method
    When user hits the end point with post method by passing valid '<id>' and '<title>' and '<author>'
    Then api should return valid '<statusCode>' in response
    And API should return created data in response body with '<id>' and '<title>' and '<author>'
    And Response should be in json format
    Examples:
      | id | title                        | author | statusCode |
      | 30 | Transforming from QA to SDET | Smruti | 201        |

#########################################PUT######################################################
  Scenario Outline: Update an user post through http put method
    When user hits the end point with put method by passing valid '<id>' and '<title>' and '<author>'
    Then api should return valid '<statusCode>' in response
    And API should return updated data in response body with '<id>' and '<title>' and '<author>'
    And Response should be in json format
    Examples:
      | id | title                           | author                     | statusCode |
      | 30 | RestAssured Framework Designing | qaautomationclasses-Smruti | 200        |

######################################PATCH######################################################
  Scenario Outline: Update an user post through http PATCH method
    When user hits the end point with patch method by passing valid '<id>'  and '<author>'
    Then api should return valid '<statusCode>' in response
    And API should return updated patch data in response body with '<id>' and '<author>'
    And Response should be in json format
    Examples:
      | id | author       | statusCode |
      | 30 | Smrutiranjan | 200        |
###############################DELETE######################################################
  Scenario Outline: delete an user post through http DELETE method
    When user hits the end point with delete method by passing valid '<id>'
    Then api should return valid '<statusCode>' in response
    And API should return not found for get call made to deleted object with '<id>'
    Examples:
      | id | statusCode |
      | 30 | 200        |