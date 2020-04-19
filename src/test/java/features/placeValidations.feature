Feature: Validating Place API's
@AddPlace
Scenario Outline: Verify add place functionality is working
	Given Add Place Payload with "<name>" "<language>" "<address>"
	When User calls "addPlaceAPI" with "POST" http request
	Then The API call got success with status code 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
	And verify place_id created maps to "<name>" using "getPlaceAPI"
	
Examples:
	| name 				| language  | address 		 |
	| House of Hoops	| English	| 123 Big Street |

@DeletePlace
Scenario: Verify if Delete Place functionality is working
	Given DeletePlace Payload
	When User calls "deletePlaceAPI" with "POST" http request
	Then The API call got success with status code 200
	And "status" in response body is "OK"