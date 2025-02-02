@E2E
Feature: To validate the Jira Responses

Background: 

Given I add the baseURI 

@post
Scenario Outline: To validate the post request and response

Given Payload of "<Summery>" and "<Description>" needs to be added for POST Request

When Call "createissue" api with "POST" HTTP method

Then Response should have the status code 201

And Response body should contain "self" key as "arun"


Examples:

|Summery|Description|
|User Not able to login1|Login functionality is not working1|
|User Not able to login2|Login functionality is not working2|
|User Not able to login3|Login functionality is not working3|
|User Not able to login4|Login functionality is not working4|


@put
Scenario: To validate the update issue with api PUT request

Given Payload need to added for PUT request

When Call "updateissue" api with "PUT" HTTP method

Then Response should have the status code 204



@get
Scenario: To validate the get issue with api GET request

When Call "getissue" api with "GET" HTTP method

Then Response should have the status code 200


@delete
Scenario: To validate the get issue with api DELETE request

When Call "deleteissue" api with "DELETE" HTTP method

Then Response should have the status code 204



