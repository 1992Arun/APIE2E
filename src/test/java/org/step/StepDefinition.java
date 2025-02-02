package org.step;

import org.junit.Assert;
import org.postresPojo.Parent;
import org.utility.APIRequests;
import org.utility.Builder;
import org.utility.UtilityClass;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class StepDefinition {

	public static RequestSpecification reqSpec;

	public static Response response;

	public static String key;

	public static Parent post;

	@Given("I add the baseURI")
	public void i_add_the_baseURI() {

		reqSpec = RestAssured.given().spec(Builder.gerRequestSpec());
	}

	@Given("Payload of {string} and {string} needs to be added for POST Request")
	public void payload_of_and_needs_to_be_added_for_POST_Request(String string, String string2) {

		reqSpec = reqSpec.body(UtilityClass.postRequestBody(string, string2));
				
	}

	@When("Call {string} api with {string} HTTP method")
	public void call_api_with_HTTP_method(String resource, String reqMethod) {

		APIRequests req = APIRequests.valueOf(resource);

		String endPoint = req.getResource();

		if (reqMethod.equalsIgnoreCase("POST")) {

			response = reqSpec.when().post(endPoint);

			post = response.as(Parent.class);

			key = post.getKey();

		} else if (reqMethod.equalsIgnoreCase("PUT")) {

			response = reqSpec.when().put(endPoint + key);

		} else if (reqMethod.equalsIgnoreCase("GET")) {

			response = reqSpec.when().get(endPoint);

		} else if (reqMethod.equalsIgnoreCase("DELETE")) {

			response = reqSpec.when().delete(endPoint);
		}

	}

	@Given("Payload need to added for PUT request")
	public void payload_need_to_added_for_PUT_request() {

		reqSpec = reqSpec.body(UtilityClass.putRequestBody());
	}

	@Then("Response should have the status code {int}")
	public void response_should_have_the_status_code(Integer int1) {

		response = response.then().spec(Builder.getResponseSpec(int1)).extract().response();

	}

	@Then("Response body should contain {string} key as {string}")
	public void response_body_should_contain_key_as(String string, String string2) {

		Assert.assertTrue(post.getSelf().contains(string2));

		Assert.assertTrue(post.getKey().contains("SCRUM"));

		System.out.println(UtilityClass.getResponseBody(response));

		System.out.println(UtilityClass.getResponseCode(response));

	}

}
