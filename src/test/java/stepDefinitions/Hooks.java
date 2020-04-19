package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {
		
		// only execute when placeId is null
		StepDefinition sd = new StepDefinition();
		
		// if variable is static, you can directly call varialbe with class name
		if (StepDefinition.placeId == null) {
			sd.add_Place_Payload_with("JD", "Tagalog", "999 ABC St");
			sd.user_calls_with_http_request("addPlaceAPI", "POST");
			sd.verify_place_id_created_maps_to_using("JD", "getPlaceAPI");
		}

	}
}
