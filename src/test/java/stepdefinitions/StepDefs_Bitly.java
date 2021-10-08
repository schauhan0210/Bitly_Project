package stepdefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;
import resources.ApiResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefs_Bitly extends Utils {

	RequestSpecification res;
	Response response;
	TestDataBuild data = new TestDataBuild();
	String bitlink_id;
	JsonPath js;
	String responseString;

	@Given("User has shortenBitlink payload with {string} and {string}")
	public void user_has_shorten_bitlink_payload_with_and(String longURL, String domain) throws IOException {
		res = given().spec(requestSpecification()).header("Authorization", getGlobalValue("accesstoken"))
				.header("Content-Type", "application/json").body(data.createShortenLinkPayload(longURL, domain));

	}
	

	@Given("User has getBitLink payload")
	public void user_has_get_bitlink_payload() throws IOException {
		res = given().spec(requestSpecification()).header("Authorization", getGlobalValue("accesstoken"))
				.header("Content-Type", "application/json");

	}

	@When("User calls {string} with {string} http request")
	public void user_calls_with_http_request(String apiResource, String methodType) throws IOException {
		ApiResources resourceapi = ApiResources.valueOf(apiResource);

		if (methodType.equalsIgnoreCase("Post")) {
			response = res.when().post(resourceapi.getResource());
			responseString = response.asString();
			js = new JsonPath(responseString);
			bitlink_id = js.getString("id");
		}
		
		else if (methodType.equalsIgnoreCase("Get")) {
			response = res.when().get(resourceapi.getResource()+"/"+bitlink_id);
			responseString = response.asString();
			System.out.println("*********");
			System.out.println("responseString" +responseString);
			System.out.println(resourceapi.getResource()+"/"+bitlink_id);
			js = new JsonPath(responseString);
			
			
		}

		js = new JsonPath(responseString);

	}

	@Then("Http response code should be {int}")
	public void http_response_code_should_be(int code) {

		assertEquals(code, response.getStatusCode());

	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String field, String expectedValue) {

		String actualValue = js.get(field);
		System.out.println("actualValue " + actualValue);
		System.out.println("expectedValue " + expectedValue);
		assertEquals(expectedValue, actualValue);

	}

}
