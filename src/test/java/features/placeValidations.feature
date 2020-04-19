Feature: Validating Place API's

Scenario: Verify add place functionality is working
	Given Add Place Payload
	When User calls "AddPlaceAPI" with Post http request
	Then The API call got success with status code 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"