package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	
	public static RequestSpecification req; // declare variable static so it can be used throughout all test executions
	
	// Add all request specifications that are mandatory throughout all APIs,
	// such as baseUrl, content type, etc
	public RequestSpecification requestSpecification() throws IOException {
		
		if (req == null) {
			PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
			
			req = new RequestSpecBuilder()
					.setBaseUri(getGlobalValue("baseUrl"))
					.addQueryParam("key", "qaclick123")
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log))
					.setContentType(ContentType.JSON)
					.build();
			return req;
		}
		return req;
		

	}
	
	// Gets value from properties file
	public static String getGlobalValue(String key) throws IOException {
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("/Users/jeremiahdeleon/projects/APIFramework/src/test/java/resources/global.properties");
		prop.load(fis);
		return prop.getProperty(key);
	}
	
	// Gets key value from JSON response
	public String getJsonPath(Response response, String key) {
		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		return js.get(key).toString();
	}
}
