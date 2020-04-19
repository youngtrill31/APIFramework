package stepDefinitions;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.Location;
import pojo.Place;

public class StepDefinition {

	ResponseSpecification resSpec;
	RequestSpecification res;
	Response response;
	
	@Given("Add Place Payload")
	public void add_Place_Payload() {

		RequestSpecification req = new RequestSpecBuilder()
				.setBaseUri("https://rahulshettyacademy.com")
				.addQueryParam("key", "qaclick123")
				.setContentType(ContentType.JSON)
				.build();
		
		
		Place p = new Place();
		p.setAccuracy(50);
		p.setName("Trap House");
		p.setPhoneNumber("805-111-1111");
		p.setAddress("120 Sesame Street Way");
		p.setLanguage("English");
		p.setWebsite("www.nonktube.com");
		List<String> types = new ArrayList<String>();
		types.add("The Trap");
		types.add("Goodies");
		p.setTypes(types);
		
		Location l = new Location();
		l.setLat(49.44);
		l.setLng(-55.11);
		p.setLocation(l);
		
		resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		res = given().spec(req).log().all()
		.body(p);
	}

	@When("User calls {string} with Post http request")
	public void user_calls_with_Post_http_request(String string) {
		
		response = res.when().post("/maps/api/place/add/json")
		.then().spec(resSpec).extract().response();
	}

	@Then("The API call got success with status code {int}")
	public void the_API_call_got_success_with_status_code(Integer int1) {

		assertEquals(response.getStatusCode(), 200);

	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String expectedValue) {
	    // Write code here that turns the phrase above into concrete actions
		
		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		assertEquals(js.get(keyValue).toString(), expectedValue);
		
	}


}
