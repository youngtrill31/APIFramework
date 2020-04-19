package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefinition extends Utils {

	ResponseSpecification resSpec;
	RequestSpecification res;
	Response response;
	TestDataBuild data = new TestDataBuild();
	static String placeId; // use static keyword so variable can used across multiple tests in the same execution
	
	@Given("Add Place Payload with {string} {string} {string}")
	public void add_Place_Payload_with(String name, String language, String address) throws IOException {
	 
		res = given().spec(requestSpecification())
		.body(data.addPlacePayload(name, language, address));
	}

	@When("User calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {
		
		// Constructor will be called with value of resource that is passed in feature file
		APIResources resourceAPI = APIResources.valueOf(resource);
		System.out.println(resourceAPI.getResource());
		
		resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		if (method.equalsIgnoreCase("POST"))
			response = res.when().post(resourceAPI.getResource());
		else if (method.equalsIgnoreCase("GET"))
			response = res.when().get(resourceAPI.getResource());
			
	}

	@Then("The API call got success with status code {int}")
	public void the_API_call_got_success_with_status_code(Integer int1) {

		assertEquals(response.getStatusCode(), 200);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String expectedValue) {

		assertEquals(getJsonPath(response, keyValue), expectedValue);	
	}

	@Then("verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {

		placeId = getJsonPath(response, "place_id");
		res = given().spec(requestSpecification())
				.queryParam("place_id", placeId);
		user_calls_with_http_request(resource, "GET");
		String actualName = getJsonPath(response, "name");
		assertEquals(actualName, expectedName);
	}
	
	@Given("DeletePlace Payload")
	public void deleteplace_Payload() throws IOException {

		res = given().spec(requestSpecification()).body(data.deletePlacePayload(placeId));
	}


}
