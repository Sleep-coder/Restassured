Feature: CRUD operations on Posts API

  Scenario: Retrieve all posts
    Given I hit the url of posts API endpoint
    When I send a GET request to retrieve all posts
    Then I receive a response with status code 200
    And the response should contain a non-empty list of posts

  Scenario: Create a new post
    Given I hit the url of posts API endpoint
    When I send a POST request to create a new post
    Then I receive a response with status code 201
    And the response should contain the created post details

  Scenario: Update an existing post
    Given I hit the url of posts API endpoint
    When I send a PUT request to update an existing post
    Then I receive a response with status code 200
    And the response should contain the updated post details

  Scenario: Delete an existing post
    Given I hit the url of posts API endpoint
    When I send a DELETE request to delete an existing post
    Then I receive a response with status code 200
